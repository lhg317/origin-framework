package com.goldgov.origin.core.cache.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisTemplateConfig extends RedisTemplate<String, Object> {

	public RedisTemplateConfig() {
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		RedisSerializer<Object> jdkSerializer = new JdkSerializationRedisSerializer();
		setKeySerializer(stringSerializer);
		setValueSerializer(jdkSerializer);
		setHashKeySerializer(stringSerializer);
		setHashValueSerializer(jdkSerializer);
	}
	
	public RedisTemplateConfig(RedisConnectionFactory connectionFactory) {
		this();
		setConnectionFactory(connectionFactory);
		afterPropertiesSet();
	}
}
