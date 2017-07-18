package com.goldgov.origin.core.cache;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.goldgov.origin.core.cache.impl.MapCacheImpl;
import com.goldgov.origin.core.cache.impl.RedisCacheImpl;
import com.goldgov.origin.core.cache.redis.RedisTemplateConfig;

import redis.clients.jedis.JedisPoolConfig;

@Configurable
public class CacheConfiguration {

	@Value("${spring.redis.host:}")
	private String host;
	
	@Value("${spring.redis.port:6379}")
	private int port;
	
	@Value("${spring.redis.password:}")
	private String password;
	
	@Bean
	@ConditionalOnProperty(name="memory.cache.enabled",havingValue="false")
	public Cache redisCache(){
		Cache cache = new RedisCacheImpl();
		return cache;
	}
	
	@Bean
	@ConditionalOnProperty(name="memory.cache.enabled",havingValue="true")
	public Cache memoryCache(){
		Cache cache = new MapCacheImpl();
		return cache;
	}
	
	@Bean
	@ConditionalOnProperty(name="memory.cache.enabled",havingValue="false")
	public RedisTemplateConfig redisTemplate(JedisConnectionFactory jedisConnectionFactory){
		return new RedisTemplateConfig(jedisConnectionFactory);
	}
	
	@Bean
	@ConditionalOnProperty(name="memory.cache.enabled",havingValue="false")
	public JedisConnectionFactory jedisConnectionFactory(){
		JedisConnectionFactory jedisConnFactory = new JedisConnectionFactory();
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
//		poolConfig.setMaxIdle(maxIdle);
//		poolConfig.setMaxTotal(maxTotal);
//		poolConfig.setMaxWaitMillis(maxWaitMillis);
		jedisConnFactory.setPoolConfig(poolConfig);
		jedisConnFactory.setHostName(host);
		jedisConnFactory.setPort(port);
		jedisConnFactory.setPassword(password);
		return jedisConnFactory;
	}
}
