package com.goldgov.origin.client;

import org.springframework.context.annotation.Bean;

public class DiscoveryClientConfiguration {

	@Bean
	public LocalServiceRegister discoveryRegister(){
		return new LocalServiceRegister();
	}
	
	@Bean
	public ClientConfig clientConfig(){
		return new ClientConfig();
	}
}
