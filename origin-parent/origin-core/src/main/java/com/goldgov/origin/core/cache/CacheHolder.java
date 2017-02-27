package com.goldgov.origin.core.cache;

import java.util.Date;

import com.goldgov.origin.core.utils.SpringBeanUtils;

/**
 * 缓存钩子类，便于缓存操作
 * @author LiuHG
 * @version 1.0
 */
public abstract class CacheHolder{

	private static Cache cache = null;
	
	public static void put(String name, Object value) {
		getCache().put(name, value);
	}

	public static void put(String name, Object value, Date expiredDate) {
		getCache().put(name, value, expiredDate);
	}
	
	public static void put(String name, Object value, long seconds) {
		getCache().put(name, value, seconds);
	}

	public static Object get(String name) {
		return getCache().get(name);
	}

	public static void remove(String name) {
		getCache().remove(name);
	}

	public static void clear() {
		getCache().clear();
	}

	public static int size() {
		return getCache().size();
	}

	public static void clearExpired() {
		getCache().clearExpired();
	}
	
	public static boolean exist(String name) {
		return getCache().exist(name);
	}

	public static void init(Cache cache){
		if(CacheHolder.cache == null){
			CacheHolder.cache = cache;
		}
	}
	
	private static Cache getCache(){
		if(cache == null){
			cache = SpringBeanUtils.getBean(Cache.class);
		}
		return cache;
	}
	
}
