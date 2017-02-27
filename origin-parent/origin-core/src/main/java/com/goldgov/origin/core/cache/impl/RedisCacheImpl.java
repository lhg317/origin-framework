package com.goldgov.origin.core.cache.impl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.goldgov.origin.core.cache.Cache;
import com.goldgov.origin.core.cache.redis.RedisTemplateConfig;

/**
 * 基于Redis的缓存实现。由于是独立的服务器，因此可以作为分布式使用，且当服务器停止后，缓存中的数据不会丢失。<br>
 * 关于Redis的更多中文的使用介绍（非官网）：http://www.redis.cn<br>
 * 缓存实现的切换，需要修改WEB-INF/spring-config.xml中的配置
 * @author LiuHG
 * @version 1.0
 * @see {@link com.goldgov.origin.core.cache.impl.MapCacheImpl MapCacheImpl}
 */
@SuppressWarnings("unchecked")
public class RedisCacheImpl implements Cache,InitializingBean{

	@Autowired(required = false)
	private RedisTemplateConfig redisTemplate;

	@Override
	public void put(final String name, final Object value) {
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] nameBytes = getNameSerializer().serialize(name);
				connection.set(nameBytes, getValueSerializer().serialize(value));
				return true;
			}
		});
	}

	@Override
	public void put(String name, Object value, Date expiredDate) {
		put(name,value);
		redisTemplate.expireAt(name, expiredDate);
	}
	
	@Override
	public void put(String name, Object value, long seconds) {
		put(name,value);
		redisTemplate.expire(name, seconds, TimeUnit.SECONDS);
	}

	@Override
	public Object get(final String name) {
		return redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] nameBytes = getNameSerializer().serialize(name);
				return getValueSerializer().deserialize(connection.get(nameBytes));
			}
		});
	}

	@Override
	public void remove(final String name) {
		redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] nameBytes = getNameSerializer().serialize(name);
				connection.del(nameBytes);
				return true;
			}
		});
	}

	@Override
	public void clear() {
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.flushDb();
				return true;
			}
		});
		
	}

	@Override
	public int size() {
		return redisTemplate.execute(new RedisCallback<Integer>() {
			@Override
			public Integer doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.dbSize().intValue();
			}
		});
	}

	@Override
	public void clearExpired() {
		throw new UnsupportedOperationException("Redis无需调用此方法来处理过期数据");
	}
	
	private RedisSerializer<String> getNameSerializer(){
		return (RedisSerializer<String>) redisTemplate
				.getKeySerializer();
	}
	
	private RedisSerializer<Object> getValueSerializer(){
		return (RedisSerializer<Object>) redisTemplate
				.getValueSerializer();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(redisTemplate == null){
			throw new RuntimeException("系统预使用Redis缓存，但是当前并未进行Redis缓存的Spring配置");
		}
	}

	@Override
	public boolean exist(final String name) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] nameBytes = getNameSerializer().serialize(name);
				return connection.exists(nameBytes);
			}
		});
	}

}
