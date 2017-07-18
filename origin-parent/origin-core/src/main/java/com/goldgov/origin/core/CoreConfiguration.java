package com.goldgov.origin.core;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.goldgov.origin.core.cache.CacheConfiguration;
import com.goldgov.origin.core.service.proxy.ProxyServiceConfigurer;

@Configurable
@Import({CacheConfiguration.class})
public class CoreConfiguration {

	@Bean
	public ProxyServiceConfigurer proxyServiceConfigurer(){
		return new ProxyServiceConfigurer();
	}
}
