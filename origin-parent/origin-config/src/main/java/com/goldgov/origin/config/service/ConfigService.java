package com.goldgov.origin.config.service;

import java.util.Map;

public interface ConfigService {

	Map<Object, Object> getConfig(String applicationName);
	
	Map<Object, Object> getConfig(String applicationName,boolean cached);
	
}
