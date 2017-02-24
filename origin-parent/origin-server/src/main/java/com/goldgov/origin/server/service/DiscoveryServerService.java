package com.goldgov.origin.server.service;

import java.util.List;
import java.util.Map;

import com.goldgov.origin.core.discovery.ServiceServer;
import com.goldgov.origin.core.discovery.ServiceServer.ServiceType;
import com.goldgov.origin.core.discovery.rpc.RpcServiceInstance;

/**
 * 服务发现中心业务接口
 * @author LiuHG
 * @since 1.0
 */
public interface DiscoveryServerService {

	/**
	 * 添加新的注册服务到服务发现中心
	 * @param service 客户端预注册的服务对象
	 */
	public void addService(ServiceServer service);
	
	public void addRequiredServiceName(String clientAddress,List<String> serviceName);
	
	public void deleteRequiredServiceName(String clientAddress);
	
	public Map<String,List<String>> getAllRequiredServiceName();
	
	/**
	 * 根据服务名获取所有的服务对象
	 * @param serviceName 服务名
	 * @return 指定服务名下的所有服务对象，通常每个服务对象代表一个服务客户端
	 */
	public List<RpcServiceInstance> getServices(String serviceName);
	
	public List<ServiceServer> getServiceServers(ServiceType serviceType);
	
	
	/**
	 * 得到所有当前服务中的服务对象，通常为了获取当前有哪些服务客户端使用。
	 * @return 服务中的服务对象
	 */
	public List<ServiceServer> getAllServiceServer();
	
	public Map<String,List<RpcServiceInstance>> getAllServices();
	
	/**
	 * 删除指定IP及端口下的所有服务，一般表示当前服务客户端不可用或宕机情况下，清除服务使用
	 * @param serverIP
	 * @param port
	 */
	public void deleteService(String serverIP,int port);
	
	public Map<String,ServiceServer> getClientMapping();
	
	public ServiceServer getService(String ip,int port);
}
