package com.goldgov.origin.core.cache.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.goldgov.origin.core.cache.Cache;

/**
 * 基于内存保存的Map形式的缓存实现。该缓存不支持在集群情况的各系统的同步，且当服务器停止后，缓存中的数据将全部丢失，
 * 但速度上是最快的。<br>
 * 缓存实现的切换，需要修改WEB-INF/spring-config.xml中的配置
 * @author LiuHG
 * @version 1.0
 * @see {@link com.goldgov.origin.core.cache.impl.RedisCacheImpl RedisCacheImpl}
 */
public class MapCacheImpl implements Cache{

	private Map<String,Object> mapCache = new ConcurrentHashMap<String,Object>();
	private Map<String,Date> expiredDate = new ConcurrentHashMap<String,Date>();
	
	@Override
	public void put(String name, Object value) {
		mapCache.put(name, value);
		expiredDate.remove(name);
	}

	@Override
	public void put(String name, Object value, Date date) {
		mapCache.put(name, value);
		expiredDate.put(name, date);
	}
	
	@Override
	public void put(String name, Object value, long seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, Long.valueOf(seconds).intValue());
		put(name,value,calendar.getTime());
	}

	@Override
	public Object get(String name) {
		Date now = new Date();
		Date expired = expiredDate.get(name);
		if(expired != null && isExpired(now,expired)){
			mapCache.remove(name);
			expiredDate.remove(name);
			return null;
		}
		return mapCache.get(name);
	}

	@Override
	public void remove(String name) {
		mapCache.remove(name);
		expiredDate.remove(name);
	}

	@Override
	public void clear() {
		mapCache.clear();
		expiredDate.clear();
	}

	@Override
	public void clearExpired() {
		Date now = new Date();
		List<String> expiredKeys = new ArrayList<String>();
		for (String name : expiredDate.keySet()) {
			Date date = expiredDate.get(name);
			if(isExpired(now,date)){
				mapCache.remove(name);
				expiredKeys.add(name);
			}
		}
		//删除已过期的缓存name，不能放在上面循环中处理。keySet随时变化会出现ConcurrentModificationException，具体原因参看Javadoc说明
		for (String name : expiredKeys) {
			expiredDate.remove(name);
		}
	}
	
	/**
	 * 判断是否过期
	 * @param now 当前时间
	 * @param expired 过期时间
	 * @return 返回true表示过期，否则不过期
	 */
	private boolean isExpired(Date now , Date expired){
		return now.after(expired);
	}

	@Override
	public int size() {
		return mapCache.size();
	}

	@Override
	public boolean exist(String name) {
		return get(name) != null;
	}

}
