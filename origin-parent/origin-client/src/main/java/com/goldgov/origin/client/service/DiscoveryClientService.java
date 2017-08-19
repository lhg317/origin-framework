package com.goldgov.origin.client.service;

/**
 * 服务发现客户端业务接口
 * @author LiuHG
 * @version 1.0
 */
public interface DiscoveryClientService {

	/**
	 * 清除目前缓存的所有服务信息
	 */
	void clearServerCache();
	
	/**
	 * 判断给定的serviceName服务是否存在
	 * @param serviceName 用于判断服务名
	 * @return 如果存在返回true，否则返回false
	 */
	boolean hasService(String serviceName);
	
	/**
	 * 更换当前服务的复杂策略
	 * @param serviceName 服务名
	 * @param strategyClass 负载策略类，需要给出全路径。
	 * @see {@link com.goldgov.origin.core.discovery.rpc.loadbalancer.impl.WeightedRoundRobinRule WeightedRoundRobinRule}
	 * @see {@link com.goldgov.origin.core.discovery.rpc.loadbalancer.impl.RoundRobinRule RoundRobinRule}
	 */
	void changeLoadBalancerStrategy(String serviceName,String strategyClass);
}
