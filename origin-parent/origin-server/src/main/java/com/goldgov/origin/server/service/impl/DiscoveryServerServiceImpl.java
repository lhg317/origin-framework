package com.goldgov.origin.server.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldgov.origin.core.discovery.ServiceServer;
import com.goldgov.origin.core.discovery.ServiceServer.ServiceType;
import com.goldgov.origin.core.discovery.http.HttpRequestClient;
import com.goldgov.origin.core.discovery.http.Response;
import com.goldgov.origin.core.discovery.http.request.DeleteRequest;
import com.goldgov.origin.core.discovery.rpc.NoServiceInstance;
import com.goldgov.origin.core.discovery.rpc.RpcServiceInstance;
import com.goldgov.origin.server.dao.DiscoveryDao;
import com.goldgov.origin.server.service.DiscoveryServerService;

/**
 * 服务发现中心业务接口实现
 * @author LiuHG
 * @since 1.0
 */
@Service
public class DiscoveryServerServiceImpl implements DiscoveryServerService{

	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private DiscoveryDao discoveryDao;
	
	private Map<String,List<String>> requiredServiceNameMap = new ConcurrentHashMap<>();
	
	@Override
	public void addService(ServiceServer serviceServer) {
		
		List<RpcServiceInstance> serviceList = serviceServer.getServiceList();
		
		if(serviceList.size() == 0){
			discoveryDao.saveService(null,new NoServiceInstance(serviceServer));
			if(logger.isDebugEnabled()){
				//FIXME 新的非一般服务被注册
				logger.debug("The new service is registered.");
			}
			return;
		}
		
		for (RpcServiceInstance service : serviceList) {
			String serviceName = service.getServiceName();
//			service.setServiceServer(serviceServer);//从json转换过来的对象为了避免堆栈溢出，这里会是null，因此将对象重新赋值
			discoveryDao.saveService(serviceName,service);
			
			if(logger.isDebugEnabled()){
				List<RpcServiceInstance> services = discoveryDao.getServices(serviceName);
				logger.debug("new service:" + serviceName + ",current num:"+services.size());
			}
		}
		
		clearServerCache();
//		sendUpdateNotify(serviceObject);
		
	}
	
	private void clearServerCache(){
		List<ServiceServer> allServiceServer = discoveryDao.getAllServiceServer();
		for (ServiceServer _serviceObject : allServiceServer) {
			DeleteRequest request = new DeleteRequest(_serviceObject.getUpdatePath());
			HttpRequestClient requestClient = new HttpRequestClient();
			try {
				Response response = requestClient.sendRequest(request);
				if(response.getStatusCode() != 200 || !response.toString().equals("SUCCESS")){
					//TODO 处理更新通知发送失败
					logger.warn("通知远程service端失败：status=" + response.getStatusCode() + ","+response.toString());
				}
			} catch (Exception e) {
				//TODO 处理更新通知发送失败
				logger.warn("通知远程service端失败：host=" + _serviceObject.getRpcServerAddress(),e);
			}finally{
				requestClient.close();
			}
		}
	}
	/*
	 * 发送通知给所有客户端更新自己的服务注册列表，如果通知发送失败，则意味着该服务端无法提供相关服务
	 */
//	private void sendUpdateNotify(ServiceServer serviceObject){
//		List<ServiceServer> serviceList = discoveryDao.getAllServices();
//		for (ServiceServer _serviceObject : serviceList) {
//			JsonRequest request = new JsonRequest(serviceObject,_serviceObject.getUpdatePath());
//			HttpRequestClient requestClient = new HttpRequestClient();
//			try {
//				Response response = requestClient.sendRequest(request);
//				if(response.getStatusCode() != 200 || !response.toString().equals("SUCCESS")){
//					//TODO 处理更新通知发送失败
//					logger.warn("通知远程service端失败：status=" + response.getStatusCode() + ","+response.toString());
//				}
//			} catch (Exception e) {
//				//TODO 处理更新通知发送失败
//				logger.warn("通知远程service端失败：host=" + _serviceObject.getRpcServerAddress(),e);
//			}finally{
//				requestClient.close();
//			}
//		}
//	}
	
//	private void sendUpdateNotify(String serviceName,RpcServiceInstance service){
//		List<ServiceObject> serviceList = discoveryDao.getServices(serviceName);
//		for (ServiceObject _serviceObject : serviceList) {
//			JsonRequest request = new JsonRequest(service,_serviceObject.getUpdatePath());
//			HttpRequestClient requestClient = new HttpRequestClient();
//			try {
//				Response response = requestClient.sendRequest(request);
//				if(response.getStatusCode() != 200 || !response.toString().equals("SUCCESS")){
//					//TODO 处理更新通知发送失败
//					logger.warn("通知远程service端失败：status=" + response.getStatusCode() + ","+response.toString());
//				}
//			} catch (Exception e) {
//				//TODO 处理更新通知发送失败
//				logger.warn("通知远程service端失败：serviceName=" + serviceName + ",host=" + service.getRpcServerAddress(),e);
//			}finally{
//				requestClient.close();
//			}
//		}
//	}
	
	@Override
	public List<RpcServiceInstance> getServices(String serviceName) {
		List<RpcServiceInstance> serviceList = discoveryDao.getServices(serviceName);
		return serviceList;
	}
	
	@Override
	public List<ServiceServer> getServiceServers(ServiceType serviceType) {
		return discoveryDao.getServiceServers(serviceType);
	}

	/*
	 * 发送通知给所有客户端将指定IP和端口的服务从自己的服务列表中全部清除，如果通知发送失败，则意味着该服务端仍然会尝试提供相关服务，
	 * 但如果尝试失败后，会再从自己的注册列表中清除。
	 */
//	private void sendDeleteNotify(String serverIP,int port){
//		//得到当前服务中的客户端服务对象，依次清除自己应用中指定IP和端口下的服务
//		List<ServiceServer> serviceObjectList = discoveryDao.getAllServices();
//		for (ServiceServer serviceObject : serviceObjectList) {
//			//如果要通知的服务为失效服务器，则跳过本次请求
//			if(serviceObject.getServerIP().equals(serverIP) && serviceObject.getServerPort() == port){
//				continue;
//			}
//			DeleteRequest request = new DeleteRequest(serviceObject.getUpdatePath() + "?ip=" + serverIP + "&port=" + port);
//			HttpRequestClient requestClient = new HttpRequestClient();
//			try {
//				Response response = requestClient.sendRequest(request);
//				if(response.getStatusCode() != 200 || !response.toString().equals("SUCCESS")){
//					//TODO 处理更新通知发送失败
//					logger.warn("通知远程service端失败：status=" + response.getStatusCode() + ","+response.toString());
//				}
//			} catch (Exception e) {
//				//TODO 处理更新通知发送失败
//				logger.warn("通知远程service端失败",e);
//			}finally{
//				requestClient.close();
//			}
//		}
//	}

	@Override
	public void deleteService(String serverIP,int port) {
		discoveryDao.deleteService(serverIP,port);
		clearServerCache();
	}

	@Override
	public List<ServiceServer> getAllServiceServer() {
		return discoveryDao.getAllServiceServer();
	}

	@Override
	public void addRequiredServiceName(String clientAddress, List<String> serviceName) {
		requiredServiceNameMap.put(clientAddress, serviceName);
	}

	@Override
	public void deleteRequiredServiceName(String clientAddress) {
		requiredServiceNameMap.remove(clientAddress);
	}

	@Override
	public Map<String,List<String>> getAllRequiredServiceName(){
		return requiredServiceNameMap;
	}
	
	@Override
	public Map<String,List<RpcServiceInstance>> getAllServices(){
		return discoveryDao.getAllServices();
	}

	@Override
	public Map<String, ServiceServer> getClientMapping() {
		return discoveryDao.getClientMapping();
	}

	@Override
	public ServiceServer getService(String ip, int port) {
		return discoveryDao.getService(ip, port);
	}

}
