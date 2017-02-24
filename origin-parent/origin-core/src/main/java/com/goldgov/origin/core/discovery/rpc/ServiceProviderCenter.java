package com.goldgov.origin.core.discovery.rpc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;

import com.goldgov.origin.core.discovery.ServiceServer;
import com.goldgov.origin.core.discovery.http.HttpRequestClient;
import com.goldgov.origin.core.discovery.http.Response;
import com.goldgov.origin.core.discovery.http.request.GetRequest;
import com.goldgov.origin.core.discovery.rpc.loadbalancer.IRule;
import com.goldgov.origin.core.discovery.rpc.loadbalancer.impl.RoundRobinRule;
import com.goldgov.origin.core.discovery.rpc.pool.SocketProvider;
import com.goldgov.origin.core.discovery.rpc.pool.SocketProviderImpl;

/**
 * 服务调度及RPC调用的核心处理类
 * @author LiuHG
 * @since 1.0
 */
public class ServiceProviderCenter{

	private final Log logger = LogFactory.getLog(getClass());
	
	private Map<String,List<RpcServiceInstance>> serviceMap = new ConcurrentHashMap<String,List<RpcServiceInstance>>();
	private Map<RpcServiceInstance,SocketProvider> socketProviderMap = new ConcurrentHashMap<RpcServiceInstance,SocketProvider>();
	
	private Map<String,IRule> ruleMap = new ConcurrentHashMap<String,IRule>();
	
//	private IRule rule;
	
	@Value("${discovery.client.discovery-server}")
	private String discoveryServer;
	
	
	public void clearServerCache(){
		serviceMap.clear();
		socketProviderMap.clear();
		ruleMap.clear();
	}
	
	public synchronized void registerService(String serviceName,RpcServiceInstance service){
		if(serviceMap.containsKey(serviceName)){
			List<RpcServiceInstance> serviceList = serviceMap.get(serviceName);
			if(!hasService(serviceList,service)){
				serviceList.add(service);
			}
		}else{
			List<RpcServiceInstance> serviceList = new ArrayList<RpcServiceInstance>();
			serviceList.add(service);
			serviceMap.put(serviceName, serviceList);
		}
		if(logger.isInfoEnabled()){
			logger.info("The new service is registered:"+serviceName+",at " + service.getServiceServer().getRpcServerAddress() + ".service num:" + serviceMap.size() + ",socketProvider:" + socketProviderMap.size());
		}
	}
	
	/**
	 * service端不可调用此方法
	 * @param serviceName
	 * @param socketProvider
	 */
	synchronized void deleteService(String serviceName,SocketProvider socketProvider){
		Set<RpcServiceInstance> keySet = socketProviderMap.keySet();
		for (RpcServiceInstance service : keySet) {
			SocketProvider _socketProvider = socketProviderMap.get(service);
			if(_socketProvider.equals(socketProvider)){
				List<RpcServiceInstance> serviceList = serviceMap.get(serviceName);
				serviceList.remove(service);
				_socketProvider.destroy();
				socketProviderMap.remove(_socketProvider);
				break;
			}
		}
	}
	
	
	public void deleteService(String serviceName){
		List<RpcServiceInstance> serviceList = serviceMap.get(serviceName);
		if(serviceList != null){
			for (RpcServiceInstance service : serviceList) {
				socketProviderMap.remove(service);
			}
			serviceMap.remove(serviceName);
		}
	}
	
	public void deleteService(String serverIP, int serverPort){
		Collection<List<RpcServiceInstance>> allServices = serviceMap.values();
		if(allServices != null && allServices.size() > 0){
			for (List<RpcServiceInstance> serviceList : allServices) {
				for (RpcServiceInstance _service : serviceList) {
					if(_service.getServiceServer().getServerIP().equals(serverIP) && _service.getServiceServer().getServerPort() == serverPort){
						socketProviderMap.remove(_service);
//						serviceMap.remove(_service);
						serviceList.remove(_service);
						break;
					}
				}
			}
		}
	}
	
	public SocketProvider getSocketProvider(String serviceName){
		List<RpcServiceInstance> serviceList = serviceMap.get(serviceName);
		//////////////////////////////////////////////////////////////////////////////////////////
		if(serviceList == null){
			List<ServiceServer> serviceObjectList = getServiceServer(serviceName);
			if(serviceObjectList != null && serviceObjectList.size() > 0){
				for (ServiceServer serviceObject : serviceObjectList) {
					registerService(serviceName,serviceObject.getService(serviceName));
				}
				serviceList = serviceMap.get(serviceName);
			}
		}
		//////////////////////////////////////////////////////////////////////////////////////////
		if(serviceList == null || serviceList.size() == 0){
			throw new RuntimeException("当前服务群中没有服务："+serviceName);
		}
		
		//负载策略
		IRule rule = ruleMap.get(serviceName);
		if(rule == null){
			//FIXME 负载策略
			rule = new RoundRobinRule();
			ruleMap.put(serviceName, rule);
		}
		RpcServiceInstance service = rule.choose(serviceList,serviceName);
		
		System.out.println(serviceName +":" + serviceList.size() + " at " + service.getServiceServer().getRpcServerAddress());//TODO DELETE
		
		SocketProvider socketProvider = socketProviderMap.get(service);
		if(socketProvider == null){
			synchronized (this) {
				//FIXME 3000秒超时设置
				socketProvider = new SocketProviderImpl(service,3000);
				socketProviderMap.put(service, socketProvider);
			}
		}
		return socketProvider;
	}
	
	protected boolean hasService(List<RpcServiceInstance> serviceList,RpcServiceInstance service){
		for (RpcServiceInstance rpcService : serviceList) {
			if(rpcService.getServiceServer().getRpcServerAddress().equals(service.getServiceServer().getRpcServerAddress())){
				return true;
			}
		}
		return false;
	}
	
	public Map<String,List<RpcServiceInstance>> getAllService(){
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
