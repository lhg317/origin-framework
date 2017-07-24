package com.goldgov.origin.core.discovery.rpc.loadbalancer.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldgov.origin.core.discovery.ServiceServer;
import com.goldgov.origin.core.discovery.rpc.loadbalancer.IRule;

/**
 * 循环负载规则
 * @author LiuHG
 * @version 1.0
 */
public class RoundRobinRule implements IRule {

	private static Logger log = LoggerFactory.getLogger(RoundRobinRule.class);
	
	private AtomicInteger nextServerCyclicCounter;

	public RoundRobinRule() {
		nextServerCyclicCounter = new AtomicInteger(0);
	}

	@Override
	public ServiceServer choose(List<ServiceServer> allServers,Object key) {
		if (allServers == null) {
			log.warn("no service ");
			return null;
		}
		ServiceServer service = null;
        int count = 0;
        while (service == null && count++ < 10) {
//            List<RpcServiceInstance> reachableServers = getReachableServers(allServers);
//            int upCount = reachableServers.size();
            int serverCount = allServers.size();

//            if ((upCount == 0) || (serverCount == 0)) {
            if (serverCount == 0) {
                log.warn("No up servers available from load balancer: " + key);
                return null;
            }

            int nextServerIndex = incrementAndGetModulo(serverCount);
            service = allServers.get(nextServerIndex);

            if (service == null) {
                Thread.yield();
                continue;
            }
            return service;
//            if (service.isgetState()) {
//                return service;
//            }
//
//            service = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                    + key);
        }
        return service;
	}

//	private List<RpcServiceInstance> getReachableServers(List<RpcServiceInstance> allServers) {
//		List<RpcServiceInstance> upServiceList = new ArrayList<>();
//		if(allServers != null){
//			for(RpcServiceInstance service : allServers){
//				if(service.getState() == ServiceState.AVAILABLE){
//					upServiceList.add(service);
//				}
//			}
//		}
//		return upServiceList;
//	}

	private int incrementAndGetModulo(int modulo) {
        for (;;) {
            int current = nextServerCyclicCounter.get();
            int next = (current + 1) % modulo;
            if (nextServerCyclicCounter.compareAndSet(current, next))
                return next;
        }
    }
	
//	public static void main(String[] args) {
//		RoundRobinRule roundRobinRule = new RoundRobinRule();
//		List<RpcServiceInstance> allServers = new ArrayList<>();
//		allServers.add(new RpcServiceInstance("a","192.168.10.11",7777));
//		allServers.add(new RpcServiceInstance("a","192.168.10.12",7777));
//		allServers.add(new RpcServiceInstance("a","192.168.10.13",7777));
//		for (int i = 0; i < 1000; i++) {
//			System.out.println(roundRobinRule.choose(allServers, "a").getRpcServerAddress());
//		}
//	}
	
}
