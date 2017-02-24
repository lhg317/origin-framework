package com.goldgov.origin.server.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.goldgov.origin.core.discovery.ServiceServer;
import com.goldgov.origin.core.discovery.ServiceServer.ServiceType;
import com.goldgov.origin.core.discovery.rpc.RpcServiceInstance;
import com.goldgov.origin.server.dao.DiscoveryDao;

/**
 * 
 * @author LiuHG
 * @since 1.0
 */
@Repository
public class MemoryDiscoveryDaoImpl implements DiscoveryDao{

	private final Log logger = LogFactory.getLog(getClass());
	
	// key:服务名（serviceName）， value:当前服务名下的服务对象集合，该服务对象中可能包含正确的List<RpcServiceInstance>
	private Map<String,List<RpcServiceInstance>> serviceMap = new HashMap<>();
	
	// key:服务端主机信息（ip+":"+port）， value:服务对象，该服务对象中不包含正确的List<RpcServiceInstance>
	// 该集合中，只包含正在提供服务的客户端主机映射信息，不包含失效的主机。
	private Map<String,ServiceServer> clientMapping = new HashMap<>();
	
	public void saveService(String serviceName,RpcServiceInstance serviceObject){
		List<RpcServiceInstance> serviceList;
		
		//FIXME 当注册服务器为非一般服务时（如webgate、配置服务，日志服务等）则没有服务名无需注册
		if(serviceName != null){
			if(serviceMap.containsKey(serviceName)){
				serviceList = serviceMap.get(serviceName);
			}else{
				serviceList = new ArrayList<>();
			}
			serviceList.add(serviceObject);
			serviceMap.put(serviceName, serviceList);
		}
		
		if(!clientMapping.containsKey(serviceObject.getServiceServer().getRpcServerAddress())){
			ServiceServer serviceServer = serviceObject.getServiceServer();
			clientMapping.put(serviceServer.getRpcServerAddress(), serviceServer);
			logger.debug("put service mapping \"" + serviceObject.getServiceServer().getRpcServerAddress() + "\"");
		}
		
//		return isNew;
		
	}
	
//	private boolean processExistService(ServiceObject serviceObject, List<ServiceObject> services) {
//		int index = 0;
//		for (ServiceObject _serviceInfo : services) {
//			if(_serviceInfo.getRpcServerAddress().equals(serviceObject.getRpcServerAddress())){
//				services.set(index, serviceObject);
//				return false;
//			}
//			index++;
//		}
//		
//		services.add(serviceObject);
//		return true;
//	}
	
	@Override
	public void updateService(String serviceName,RpcServiceInstance serviceObject) {
		List<RpcServiceInstance> serviceList = serviceMap.get(serviceName);
		for (int i = 0; i < serviceList.size(); i++) {
			RpcServiceInstance _serviceInfo = serviceList.get(i);
			if(_serviceInfo.getServiceServer().getRpcServerAddress().equals(serviceObject.getServiceServer().getRpcServerAddress())){
				serviceList.set(i, serviceObject);
			}
		}
	}

	@Override
	public List<RpcServiceInstance> getServices(String serviceName) {
		List<RpcServiceInstance> serviceList = serviceMap.get(serviceName);
		if(serviceList == null){
			serviceList = Collections.emptyList();
		}
		return serviceList;
	}

	@Override
	public void deleteService(String serverIP,int port) {
		Set<String> keySet = serviceMap.keySet();
		clientMapping.remove(serverIP+":"+port);
		for (String serviceName : keySet) {
			List<RpcServiceInstance> serviceList = serviceMap.get(serviceName);
			for (RpcServiceInstance serviceObject : serviceList) {
				ServiceServer serviceServer = serviceObject.getServiceServer();
				if(serviceServer.getServerIP().equals(serverIP) && serviceServer.getServerPort() == port){
					serviceList.remove(serviceObject);
					logger.debug("delete service \"" + serviceName + "\" at " + serviceServer.getRpcServerAddress());
					break;
				}
			}
		}
	}

	@Override
	public ServiceServer getService(String ip, int port) {
		return getService(ip+":"+port);
	}
	
	@Override
	public ServiceServer getService(String clientAddress) {
		return clientMapping.get(clientAddress);
	}

	@Override
	public List<ServiceServer> getAllServiceServer() {
		List<ServiceServer> serviceObjectList = new ArrayList<>(clientMapping.size());
		for (ServiceServer serviceObject : clientMapping.values()) {
			serviceObjectList.add(serviceObject);
		}
		return serviceObjectList;
	}

	@Override
	public Map<String, List<RpcServiceInstance>> getAllServices() {
		return serviceMap;
	}
	
	@Override
	public Map<String,ServiceServer> getClientMapping(){
		return clientMapping;
	}

	@Override
	public List<ServiceServer> getServiceServers(ServiceType serviceType) {
		List<ServiceServer> result = new ArrayList<>();
		Collection<ServiceServer> serviceServers = clientMapping.values();
		for (ServiceServer serviceServer : serviceServers) {
			ServiceType[] serviceTypes = serviceServer.getServiceType();
			if(hasServiceType(serviceType,serviceTypes)){
				result.add(serviceServer);
			}
		}
		return result;
	}

	private boolean hasServiceType(ServiceType serviceType,ServiceType[] serviceTypes){
		for (ServiceType _serviceType : serviceTypes) {
			if(_serviceType == serviceType){
				return true;
			}
		}
		return false;
	}
}
