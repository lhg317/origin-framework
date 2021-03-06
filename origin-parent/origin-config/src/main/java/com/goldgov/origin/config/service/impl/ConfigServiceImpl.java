package com.goldgov.origin.config.service.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.goldgov.origin.config.service.ConfigService;
import com.goldgov.origin.core.cache.CacheHolder;

@Service
public class ConfigServiceImpl implements ConfigService{

	private final Log logger = LogFactory.getLog(getClass());
	
	@Value("${config.location:}")
	private String configLocation;
	
	@Override
	public Map<Object, Object> getConfig(String applicationName) {
		if(configLocation.length() > 0 && !configLocation.endsWith("/")){
			configLocation += "/";
		}
		
		configLocation += applicationName + ".properties";
		
		Properties properties;
		try {
			properties = PropertiesLoaderUtils.loadProperties(new PathResource(ResourceUtils.getFile(configLocation).getPath()));
		} catch (Exception e) {
			logger.warn("获取统一配置时发生错误，请确认是否提供了统一配置：" + applicationName,e);
			return Collections.emptyMap();
		}
		
		return properties;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<Object, Object> getConfig(String applicationName,boolean cached) {
		Map<Object, Object> returnValue = null;
		if(cached){
			returnValue = (Map<Object, Object>) CacheHolder.get(this.getClass().getName());
		}
		
		if(returnValue == null){
			returnValue = getConfig(applicationName);
			CacheHolder.put(this.getClass().getName(), returnValue);
		}
		
		return returnValue;
	}

}
