package com.goldgov.origin.core.cache;

import java.util.Date;

/**
 * 数据缓存接口
 * @author LiuHG
 * @version 1.0
 */
public interface Cache {

	/**
	 * 将对象加入到缓存中
	 * @param name 缓存对象对应的名称
	 * @param value 需要缓存的对象
	 */
	public void put(String name,Object value);
	
	/**
	 * 将对象加入到缓存中
	 * @param name 缓存对象对应的名称
	 * @param value 需要缓存的对象
	 * @param expiredDate 缓存对象的过期时间
	 */
	public void put(String name, Object value, Date expiredDate);
	public void put(String name, Object value, long seconds);
	/**
	 * 根据<code>name</code>获取缓存对象，首先会先检测缓存对象是否过期。
	 * @param name 缓存对象对应的名称
	 * @return 缓存对象，如果缓存对象不存在或已过期会返回<code>null</code>
	 */
	public Object get(String name);
	
	/**
	 * 根据<code>name</code>删除缓存对象
	 * @param name 缓存对象对应的名称
	 */
	public void remove(String name);
	
	/**
	 * 清除所有缓存对象，包括未过期的对象
	 */
	public void clear();
	
	/**
	 * 当前缓存中的数量
	 */
	public int size();
	
	/**
	 * 清除所有已过期的缓存对象
	 */
	public void clearExpired();
	
	public boolean exist(String name);
	
//	/**
//	 * 当前缓存是否启用
//	 * @return true 启用，false 未启用
//	 */
//	public boolean isEnabled();
}
