package com.goldgov.origin.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = ClientConfig.PREFIX)
public class ClientConfig {

	public static final String PREFIX = "discovery.client";
	
	private String discoveryServer;
	private String userName;
	private String password;
	public String getDiscoveryServer() {
		return discoveryServer;
	}
	public void setDiscoveryServer(String discoveryServer) {
		this.discoveryServer = discoveryServer;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
