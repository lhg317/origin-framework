package com.goldgov.origin.core.discovery.rpc.loadbalancer;

import java.util.List;

import com.goldgov.origin.core.discovery.rpc.RpcServiceInstance;

/**
 * 负载策略
 * @author LiuHG
 * @version 1.0
 */
public interface IRule {

	/**
	 * 服务选择策略
	 * @param servers 当前可用服务实例
	 * @param key 服务标识名，此处为服务标识名
	 * @return 根据负载策略计算后的服务实例
	 */
	public RpcServiceInstance choose(List<RpcServiceInstance> servers,Object key);

}
