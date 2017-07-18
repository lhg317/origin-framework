package com.goldgov.origin.server.dao;

import java.util.List;
import java.util.Map;

import com.goldgov.origin.core.discovery.ServiceServer;
import com.goldgov.origin.core.discovery.ServiceServer.ServiceType;
import com.goldgov.origin.core.discovery.rpc.RpcServiceInstance;

public interface DiscoveryDao {

	public void saveService(String serviceName,RpcServiceInstance serviceObject);
	
//	public void updateService(String serviceName,RpcServiceInstance serviceObject);

	public List<RpcServiceInstance> getServices(String serviceName);

	public List<ServiceServer> getServiceServers(ServiceType serviceType);
	
	public Map<String,List<RpcServiceInstance>> getAllServices();
	
	public List<ServiceServer> getAllServiceServer();
	
	public ServiceServer getService(String ip,int port);
	
	public ServiceServer getService(String clientAddress);
	
	public void deleteService(String serverIP,int port);
	
	public Map<String,ServiceServer> getClientMapping();

	
	
}
