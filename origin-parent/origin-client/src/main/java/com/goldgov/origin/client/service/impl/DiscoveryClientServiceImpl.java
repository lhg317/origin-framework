package com.goldgov.origin.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldgov.origin.client.service.DiscoveryClientService;
import com.goldgov.origin.core.discovery.rpc.ServiceProviderCenter;

@Service
public class DiscoveryClientServiceImpl implements DiscoveryClientService{

	@Autowired
	private ServiceProviderCenter serviceProviderCenter;
	
	@Override
	public void clearServerCache() {
		serviceProviderCenter.clearServerCache();
	}
}
