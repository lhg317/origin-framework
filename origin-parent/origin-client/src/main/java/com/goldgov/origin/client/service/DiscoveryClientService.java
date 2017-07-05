package com.goldgov.origin.client.service;

public interface DiscoveryClientService {

	void clearServerCache();
	
	boolean hasService(String serviceName);
	
	void changeLoadBalancerStrategy(String serviceName,String strategyClass);
}
