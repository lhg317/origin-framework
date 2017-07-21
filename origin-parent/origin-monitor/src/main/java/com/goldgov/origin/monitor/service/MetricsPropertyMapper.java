package com.goldgov.origin.monitor.service;

import java.util.Map;

public interface MetricsPropertyMapper {

	MonitorInfo getMonitorInfo(Map<String,Number> propValueMap);
	
}
