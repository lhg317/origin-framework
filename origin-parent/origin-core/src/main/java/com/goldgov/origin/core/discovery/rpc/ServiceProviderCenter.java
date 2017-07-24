package com.goldgov.origin.core.discovery.rpc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.GaugeService;

import com.goldgov.origin.core.discovery.ServiceServer;
import com.goldgov.origin.core.discovery.http.HttpRequestClient;
import com.goldgov.origin.core.discovery.http.Response;
import com.goldgov.origin.core.discovery.http.request.GetRequest;
import com.goldgov.origin.core.discovery.rpc.loadbalancer.IRule;
import com.goldgov.origin.core.discovery.rpc.loadbalancer.impl.WeightedRoundRobinRule;
import com.goldgov.origin.core.discovery.rpc.pool.SocketProvider;
import com.goldgov.origin.core.discovery.rpc.pool.SocketProviderImpl;

/**
 * 服务调度及RPC调用的核心处理类
 * @author LiuHG
 * @since 1.0
 */
public class ServiceProviderCenter{

	private final Log logger = LogFactory.getLog(getClass());
	
	private Map<String,List<ServiceServer>> serviceMap = new ConcurrentHashMap<String,List<ServiceServer>>();
	//key：ServiceServer.getServerID(); 服务器ID，value：一个TSocket对象池，每台服务器一个池
	private Map<String,SocketProvider> socketProviderMap = new ConcurrentHashMap<String,SocketProvider>();
	
	private Map<String,IRule> ruleMap = new ConcurrentHashMap<String,IRule>();
	
//	private IRule rule;
	
	@Value("${discovery.client.discovery-server}")
	private String discoveryServer;
	
	@Value("${rpc.connect.timeout:3000}")
	private int rpcTimeout;
	
	@Autowired(required=false)
	private GaugeService gaugeService;
	
	public void clearServerCache(){
		serviceMap.clear();
		socketProviderMap.clear();
		ruleMap.clear();
	}
	
	@SuppressWarnings("unchecked")
	public void changeLoadBalancerStrategy(String serviceName,String strategyClass){
		Class<IRule> forName;
		try {
			forName = (Class<IRule>) Class.forName(strategyClass);
			ruleMap.put(serviceName, forName.newInstance());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("负载策略类文件不存在：" + strategyClass,e);
		} catch (Exception e) {
			throw new RuntimeException("实例化负载策略类失败，请保证无参构造器：" + strategyClass,e);
		}
	}
	
	public synchronized void registerService(String serviceName,ServiceServer server){
		if(serviceMap.containsKey(serviceName)){
			List<ServiceServer> serviceList = serviceMap.get(serviceName);
			if(!hasService(serviceList,server)){
				serviceList.add(server);
			}
		}else{
			List<ServiceServer> serverList = new ArrayList<ServiceServer>();
			serverList.add(server);
			serviceMap.put(serviceName, serverList);
		}
		if(logger.isInfoEnabled()){
			logger.info("The new service is registered:"+serviceName+",at " + server.getRpcServerAddress() + ".service num:" + serviceMap.size() + ",socketProvider:" + socketProviderMap.size());
		}
	}
	
	/**
	 * service端不可调用此方法
	 * @param serviceName
	 * @param socketProvider
	 */
	synchronized void deleteService(String serviceName,SocketProvider socketProvider){
		Set<String> keySet = socketProviderMap.keySet();
		for (String serverID : keySet) {
			SocketProvider _socketProvider = socketProviderMap.get(serverID);
			if(_socketProvider.equals(socketProvider)){
				List<ServiceServer> serviceList = serviceMap.get(serviceName);
				for (ServiceServer serviceServer : serviceList) {
					if(serviceServer.getServerID().equals(serverID)){
						serviceList.remove(serviceServer);
					}
				}
				_socketProvider.destroy();
				socketProviderMap.remove(_socketProvider);
				break;
			}
		}
	}
	
	
//	public void deleteService(String serviceName){
//		List<ServiceServer> serviceList = serviceMap.get(serviceName);
//		if(serviceList != null){
//			for (ServiceServer server : serviceList) {
//				socketProviderMap.remove(server);
//			}
//			serviceMap.remove(serviceName);
//		}
//	}
//	
//	public void deleteService(String serverIP, int serverPort){
//		Collection<List<ServiceServer>> allServer = serviceMap.values();
//		if(allServer != null && allServer.size() > 0){
//			for (List<ServiceServer> serverList : allServer) {
//				for (ServiceServer _server : serverList) {
//					if(_server.getServerIP().equals(serverIP) && _server.getServerPort() == serverPort){
//						socketProviderMap.remove(_server);
////						serviceMap.remove(_service);
//						serverList.remove(_server);
//						break;
//					}
//				}
//			}
//		}
//	}
	
	public SocketProvider getSocketProvider(String serviceName){
		List<ServiceServer> serverList = serviceMap.get(serviceName);
		//////////////////////////////////////////////////////////////////////////////////////////
		if(serverList == null || serverList.size() == 0){
			List<ServiceServer> serviceObjectList = getServiceServer(serviceName);
			if(serviceObjectList != null && serviceObjectList.size() > 0){
				for (ServiceServer serviceObject : serviceObjectList) {
					/*
					 * 虽然返回当前服务的服务器对象可能还有其他很多服务，但是此处并不要对其进行注册，
					 * 只有当需要对应服务的时候才去获取新的。
					 */
					registerService(serviceName,serviceObject);
//					List<RpcServiceInstance> serviceList = serviceObject.getServiceList();
//					for (RpcServiceInstance rpcServiceInstance : serviceList) {
//						registerService(rpcServiceInstance.getServiceName(),serviceObject);
//					}
				}
				serverList = serviceMap.get(serviceName);
			}
		}
		//////////////////////////////////////////////////////////////////////////////////////////
		if(serverList == null || serverList.size() == 0){
			throw new RuntimeException("当前服务群中没有服务："+serviceName);
		}
		
		//负载策略
		IRule rule = ruleMap.get(serviceName);
		if(rule == null){
			//FIXME 负载策略
			rule = new WeightedRoundRobinRule();
			ruleMap.put(serviceName, rule);
		}
		ServiceServer server = rule.choose(serverList,serviceName);
		
		if(logger.isDebugEnabled()){
			logger.debug(serviceName +":(" + serverList.size() + ") at " + server.getRpcServerAddress());
		}
		
		SocketProvider socketProvider = socketProviderMap.get(server);
		if(socketProvider == null){
			synchronized (this) {
				//秒超时设置
				socketProvider = new SocketProviderImpl(server,rpcTimeout);
				socketProviderMap.put(server.getServerID(), socketProvider);
			}
		}
		if(gaugeService != null){
			gaugeService.submit("rpc." + serviceName, serverList.size());
			gaugeService.submit("rpc.pool.num-active."+server.getRpcServerAddress(), socketProvider.getNumActive());
			gaugeService.submit("rpc.pool.max-total."+server.getRpcServerAddress(), socketProvider.getMaxTotal());
		}
		return socketProvider;
	}
	
	protected boolean hasService(List<ServiceServer> serverList,ServiceServer server){
		for (ServiceServer rpcServer : serverList) {
			if(rpcServer.getServerID().equals(server.getServerID())){
				return true;
			}
		}
		return false;
	}
	
	public Map<String,List<ServiceServer>> getAllService(){
		return serviceMap;
	}

	public List<ServiceServer> getServiceServer(String serviceName){
		GetRequest request = new GetRequest(discoveryServer + "?serviceName=" + serviceName);
		HttpRequestClient requestClient = new HttpRequestClient();
		Response response = null;
		try {
			response = requestClient.sendRequest(request);
			ServiceServer[] services = null;
			if(response.getStatusCode() == 200){
				services = response.toObject(ServiceServer[].class);
			}else{
				//FIXME
			}
			logger.debug(" Get "+ services.length +" " + serviceName +" service producers.from " + discoveryServer);
			return Arrays.asList(services);
		} catch (Exception e) {
			throw new RuntimeException("获取服务时发生错误：" + serviceName ,e);
		}finally{
			requestClient.close();
		}
	}
}
