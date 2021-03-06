package com.goldgov.origin.core.discovery;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.goldgov.origin.core.discovery.rpc.RpcServiceInstance;

/**
 * 服务端对象信息
 * @author LiuHG
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true) 
public class ServiceServer {

	private String serverID;
	
	private String applicationName;
	private String displayName;	
	
	private String serverIP;//rpc服务器的IP
	private String serverName;//rpc服务器的机器名
	private int serverPort = -1;//rpc服务器的RPC端口,如果该应用中没有服务，则该值为-1
	
	private String webPath;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date registerDate;
	
	private ServiceType[] serviceType;
	
	private double weights;//服务权重
	
	@JsonManagedReference
	private List<RpcServiceInstance> serviceList = new ArrayList<>();
	
//	private List<String> requiredServerNames = new ArrayList<>();
//	private List<String> optionalServerNames = new ArrayList<>();
	
	private Map<String,ServiceDependency> dependencyMap = new HashMap<>();
	
	public RpcServiceInstance getService(String serviceName){
		for (RpcServiceInstance serviceInstance : serviceList) {
			if(serviceInstance.getServiceName().equals(serviceName)){
				return serviceInstance;
			}
		}
		throw new RuntimeException("no service " + serviceName + " at " + getRpcServerAddress());
	}
	
	public void addService(RpcServiceInstance service){
		serviceList.add(service);
	}
	
//	public void addRequiredServerName(String serviceName){
//		requiredServerNames.add(serviceName);
//	}
//	
//	public void addOptionalServerName(String serviceName){
//		optionalServerNames.add(serviceName);
//	}
	
	public void putServiceDependency(String serviceName,ServiceDependency dependencyType){
		dependencyMap.put(serviceName,dependencyType);
	}
	
	public Map<String, ServiceDependency> getServiceDependency(){
		return dependencyMap;
	}
	

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	/**
	 * Rpc服务器的RPC端口,如果该应用中没有服务，则该值为-1
	 * @return
	 */
	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public List<RpcServiceInstance> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<RpcServiceInstance> serviceList) {
		this.serviceList = serviceList;
	}
	
	public String getRpcServerAddress(){
		return serverIP + ":" + serverPort;
	}

//	public List<String> getRequiredServerNames() {
//		return requiredServerNames;
//	}
//	
//	public List<String> getOptionalServerNames() {
//		return optionalServerNames;
//	}

	public ServiceType[] getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType[] serviceType) {
		this.serviceType = serviceType;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public double getWeights() {
		return weights;
	}

	public void setWeights(double weights) {
		this.weights = weights;
	}
	
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getServerID() {
		return serverID;
	}

	public void setServerID(String serverID) {
		this.serverID = serverID;
	}

	public String getWebPath() {
		return webPath;
	}

	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}

	public String getHealthPath() {
		Assert.notNull(webPath,"webPath is required");
		return webPath + "/health";
	}

	public String getUpdatePath() {
		Assert.notNull(webPath,"webPath is required");
		return webPath + "/update";
	}

	public String getConfigPath() {
		Assert.notNull(webPath,"webPath is required");
		return webPath + "/config";
	}
	
	public String getMetricsPath(){
		Assert.notNull(webPath,"webPath is required");
		return webPath + "/metrics";
	}

	
	public enum ServiceDependency{
		REQUIRED,OPTIONAL,JUST_PROVIDER;
	}
	
}
