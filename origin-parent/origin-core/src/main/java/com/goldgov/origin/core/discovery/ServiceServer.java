package com.goldgov.origin.core.discovery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	private String applicationName;
	private String displayName;	
	
	private String serverIP;
	private String serverName;
	private int serverPort;
	
	private String healthPath;
	private String updatePath;
	private String configPath;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date registerDate;
	
	private ServiceType[] serviceType;
	
	private double weights;//服务权重
	
	@JsonManagedReference
	private List<RpcServiceInstance> serviceList = new ArrayList<>();
	
	private List<String> requiredServerNames = new ArrayList<>();
	private List<String> optionalServerNames = new ArrayList<>();
	
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
	
	public void addRequiredServerName(String serviceName){
		requiredServerNames.add(serviceName);
	}
	
	public void addOptionalServerName(String serviceName){
		optionalServerNames.add(serviceName);
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getHealthPath() {
		return healthPath;
	}

	public void setHealthPath(String healthPath) {
		this.healthPath = healthPath;
	}

	public String getUpdatePath() {
		return updatePath;
	}

	public void setUpdatePath(String updatePath) {
		this.updatePath = updatePath;
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

	public List<String> getRequiredServerNames() {
		return requiredServerNames;
	}
	
	public List<String> getOptionalServerNames() {
		return optionalServerNames;
	}

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

	public String getConfigPath() {
		return configPath;
	}

	public void setConfigPath(String configPath) {
		this.configPath = configPath;
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

	public enum ServiceType{
		ProducerService,//生产者服务
		ConsumerService,//消费者服务
		ConfigurationService,//配置服务
		MonitorService,//监控服务
		LoggingService,//日志服务
		CertificationService,//认证服务
		DiscoveryService,//发现服务
		WebGateService,//网关服务
		NoService;//没有任何标准服务
	}

	
}
