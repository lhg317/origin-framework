package com.goldgov.origin.core.discovery.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.goldgov.origin.core.discovery.rpc.ServiceProviderCenter;

@ConfigurationProperties(prefix="endpoints.rpc",ignoreUnknownFields=false)
public class RpcServiceEndPoint extends AbstractEndpoint<Map<String,Object>> implements ApplicationContextAware{

	public RpcServiceEndPoint() {
		super("rpc");
	}

	private ApplicationContext applicationContext;

	@Override
	public Map<String,Object> invoke() {
		ServiceProviderCenter serviceCenter = applicationContext.getBean(ServiceProviderCenter.class);
		Map<String,Object> serviceMap = new HashMap<>();
		serviceMap.put("rpcServices", serviceCenter.getAllService());
		return serviceMap;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
