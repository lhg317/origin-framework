package com.goldgov.origin.core.discovery.rpc;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.goldgov.origin.core.discovery.ServiceServer;

/**
 * 一个服务实例，包含服务名，服务显示名、服务类型等信息
 * @author LiuHG
 * @since 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true) 
public class RpcServiceInstance implements Serializable{

	private static final long serialVersionUID = -7334101390524973702L;
	
	private String serviceName;
	private String displayName;
	
	private double weights;//服务权重
	
	
	//由于对象循环嵌套，JSON无法支持，会变成 无限嵌套
	@JsonBackReference
	private ServiceServer serviceServer;

	public RpcServiceInstance(){
		serviceServer = new ServiceServer();
	}
	
//	public RpcServiceInstance(String serviceName){
//		this.serviceName = serviceName;
//	}
	
	public RpcServiceInstance(String serviceName,ServiceServer serviceServer){
		this.serviceName = serviceName;
		this.serviceServer = serviceServer;
	}
	
//	public RpcServiceInstance(String serviceName,String serverIP, int serverPort){
//		this.serviceName = serviceName;
//		this.serverIP = serverIP;
//		this.serverPort = serverPort;
//	}
//	
//	/**
//	 * 
//	 * @param serviceName
//	 * @param serviceIP
//	 * @param servicePort
//	 * @param weights 当前服务权重
//	 */
//	public RpcServiceInstance(String serviceName,String serviceIP, int servicePort,double weights){
//		this.serviceName = serviceName;
//		this.serverIP = serviceIP;
//		this.serverPort = servicePort;
//		this.weights = weights;
//	}
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getWeights() {
		return weights;
	}

	public void setWeights(double weights) {
		this.weights = weights;
	}
	
	public String getDisplayName() {
		if(displayName == null){
			return serviceName;
		}
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public ServiceServer getServiceServer() {
		return serviceServer;
	}

	public void setServiceServer(ServiceServer serviceServer) {
		this.serviceServer = serviceServer;
	}


}
