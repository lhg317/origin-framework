package com.goldgov.origin.core;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.goldgov.origin.core.cache.Cache;
import com.goldgov.origin.core.cache.impl.MapCacheImpl;
import com.goldgov.origin.core.dao.MyBatisConfiguration;

@Configurable
@Import(MyBatisConfiguration.class)
public class CoreConfiguration {

	@Bean
	public Cache cache(){
		Cache cache = new MapCacheImpl();
		return cache;
	}
}
