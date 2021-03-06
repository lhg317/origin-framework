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
import com.goldgov.origin.core.discovery.ServiceType;
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
	
	
//	private Map<String,List<String>> requiredServiceNameMap = new ConcurrentHashMap<>();
//	private Map<String,List<String>> optionalServiceNameMap = new ConcurrentHashMap<>();
	
	public void saveService(String serviceName,RpcServiceInstance serviceObject){
		List<RpcServiceInstance> serviceList;
		
		//FIXME 当注册服务器为非一般服务时（如webgate、配置服务，日志服务等）则没有服务名无需注册
		if(serviceName != null){
			if(serviceMap.containsKey(serviceName)){
				serviceList = serviceMap.get(serviceName);
			}else{
				serviceList = new ArrayList<>();
			}
			boolean hasService = serviceIsExist(serviceObject, serviceList);
			if(!hasService){
				serviceList.add(serviceObject);
				serviceMap.put(serviceName, serviceList);
			}
		}
//		else if(serviceObject instanceof NoServiceInstance){
//			NoServiceInstance noServiceInstance = (NoServiceInstance)serviceObject;
//			ServiceServer serviceServer = noServiceInstance.getServiceServer();
//			clientMapping.put(serviceServer.getServerID(), serviceServer);
//		}
		
		if(!clientMapping.containsKey(serviceObject.getServiceServer().getServerID())){
			ServiceServer serviceServer = serviceObject.getServiceServer();
			clientMapping.put(serviceServer.getServerID(), serviceServer);
//			addRequiredServiceName(serviceServer.getServerID(), serviceServer.getRequiredServerNames());
//			addOptionalServiceName(serviceServer.getServerID(), serviceServer.getOptionalServerNames());
		}
		
	}

	private boolean serviceIsExist(RpcServiceInstance serviceObject, List<RpcServiceInstance> serviceList) {
		boolean hasService = false;
		for(RpcServiceInstance serviceInstance : serviceList){
			String address1 = serviceInstance.getServiceServer().getRpcServerAddress();
			String address2 = serviceObject.getServiceServer().getRpcServerAddress();
			if(address1.equals(address2)){
				hasService = true;
				break;
			}
		}
		return hasService;
	}
	
//	@Override
//	public void updateService(String serviceName,RpcServiceInstance serviceObject) {
//		List<RpcServiceInstance> serviceList = serviceMap.get(serviceName);
//		for (int i = 0; i < serviceList.size(); i++) {
//			RpcServiceInstance _serviceInfo = serviceList.get(i);
//			if(_serviceInfo.getServiceServer().getRpcServerAddress().equals(serviceObject.getServiceServer().getRpcServerAddress())){
//				serviceList.set(i, serviceObject);
//			}
//		}
//	}

	@Override
	public List<RpcServiceInstance> getServices(String serviceName) {
		List<RpcServiceInstance> serviceList = serviceMap.get(serviceName);
		if(serviceList == null){
			serviceList = Collections.emptyList();
		}
		return Collections.unmodifiableList(serviceList);
	}

	@Override
	public void deleteServiceServer(String serverID) {
		Set<String> keySet = serviceMap.keySet();
		clientMapping.remove(serverID);
		for (String serviceName : keySet) {
			List<RpcServiceInstance> serviceList = serviceMap.get(serviceName);
			for (RpcServiceInstance serviceObject : serviceList) {
				ServiceServer serviceServer = serviceObject.getServiceServer();
				if(serviceServer.getServerID().equals(serverID)){
					serviceList.remove(serviceObject);
					logger.info("delete service \"" + serviceName + "\" at " + serviceServer.getRpcServerAddress());
					break;
				}
			}
		}
	}

	@Override
	public ServiceServer getServiceServer(String serverID) {
		return clientMapping.get(serverID);
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
	
//	@Override
//	public void addRequiredServiceName(String serverID, List<String> serviceName) {
//		requiredServiceNameMap.put(serverID, serviceName);
//	}
//
//	@Override
//	public void deleteRequiredServiceName(String serverID) {
//		requiredServiceNameMap.remove(serverID);
//	}
//
//	@Override
//	public Map<String,List<String>> getAllRequiredServiceName(){
//		return requiredServiceNameMap;
//	}
//	
//	@Override
//	public void addOptionalServiceName(String serverID, List<String> serviceName) {
//		optionalServiceNameMap.put(serverID, serviceName);
//	}
//	
//	@Override
//	public void deleteOptionalServiceName(String serverID) {
//		optionalServiceNameMap.remove(serverID);
//	}
//	
//	@Override
//	public Map<String,List<String>> getAllOptionalServiceName(){
//		return optionalServiceNameMap;
//	}

	private boolean hasServiceType(ServiceType serviceType,ServiceType[] serviceTypes){
		for (ServiceType _serviceType : serviceTypes) {
			if(_serviceType == serviceType){
				return true;
			}
		}
		return false;
	}
}
