package com.goldgov.origin.server.dao;

import java.util.List;
import java.util.Map;

import com.goldgov.origin.core.discovery.ServiceServer;
import com.goldgov.origin.core.discovery.ServiceType;
import com.goldgov.origin.core.discovery.rpc.RpcServiceInstance;

public interface DiscoveryDao {

	public void saveService(String serviceName,RpcServiceInstance serviceObject);
	
//	public void updateService(String serviceName,RpcServiceInstance serviceObject);

	public List<RpcServiceInstance> getServices(String serviceName);

	public List<ServiceServer> getServiceServers(ServiceType serviceType);
	
	public Map<String,List<RpcServiceInstance>> getAllServices();
	
	public List<ServiceServer> getAllServiceServer();
	
	public ServiceServer getServiceServer(String serverID);
	
	public void deleteServiceServer(String serverID);
	
	public Map<String,ServiceServer> getClientMapping();
	
	
	
//	public void addRequiredServiceName(String serverID,List<String> serviceName);
//	
//	public void addOptionalServiceName(String serverID, List<String> serviceName);
	
//	public void deleteRequiredServiceName(String serverID);
//	
//	public void deleteOptionalServiceName(String serverID);
	
//	public Map<String,List<String>> getAllRequiredServiceName();
//	
//	public Map<String,List<String>> getAllOptionalServiceName();

	
	
}
