package com.goldgov.origin.monitor.service;

import java.util.List;

import com.goldgov.origin.core.discovery.ServiceServer;

public interface MonitorService {

	public List<ServiceServer> getAllMonitorServer();
	
	public List<MonitorInfo> getAllServerMonitorInfo();
	
}
