package com.goldgov.origin.core.discovery.rpc.loadbalancer.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldgov.origin.core.discovery.ServiceServer;

/**
 * 加权循环负载规则<p>
 * 针对某个RPC服务，根据给定的权重分配服务器，用于针对服务器配置不同、服务器地域不同、单机服务数量不均衡匹配的情况。如果同一个服务有4个端点：<p>
 * A（wt = 5），B（wt = 40），C （wt = 100），D（wt = 20）。
 * 使用随机API，生成0到40之间的随机数。 基于权重，间隔如下：<p>
 * 0-----5	(A服务器权重期间) 05%的服务几率<br>
 * 6-----20 (C服务器权重期间) 15%的服务几率<br>
 * 21----40 (B服务器权重期间) 20%的服务几率<br>
 * 41----100(D服务器权重期间) 60%的服务几率<br>
 * <p>
 * 如果所有端的最大权重为0，则采用循环负载策略。
 * @author LiuHG
 * @version 1.0
 *
 */
public class WeightedRoundRobinRule extends RoundRobinRule{

	private static Logger log = LoggerFactory.getLogger(RoundRobinRule.class);
	
	private final Random random = new Random();
	
	private WeightComparator weightComparator = new WeightComparator();
	
	@Override
	public ServiceServer choose(List<ServiceServer> allServers, Object key) {
		
		if (allServers == null) {
			log.warn("no service ");
			return null;
		}
		ServiceServer server = null;
		
        while (server == null) {
        	
            if (Thread.interrupted()) {
                return null;
            }
            
            if (allServers.size() == 0) {
                return null;
            }
            
            int serverIndex = 0;
            
            
            //得到最大权重值
            double maxTotalWeight = 0;// = currentWeights.size() == 0 ? 0 : currentWeights.get(currentWeights.size() - 1); 
            if(allServers.size() > 0){
            	sortByWeight(allServers);
            	ServiceServer maxWeightService = allServers.get(allServers.size() - 1);
            	maxTotalWeight = maxWeightService.getWeights();
            }
            
            if (maxTotalWeight < 0.001d) {
                server =  super.choose(allServers, key);
                if(server == null) {
                    return server;
                }
            } else {
                // 生成一个从0（包含）到最大权重值（不含）的随机数
                double randomWeight = random.nextDouble() * maxTotalWeight;
                // 基于随机数权重取出对应的RPC服务索引号
                int n = 0;
                for (ServiceServer service : allServers) {
                    if (service.getWeights() >= randomWeight) {
                        serverIndex = n;
                    } else {
                        n++;
                    }
                }

                server = allServers.get(serverIndex);
            }
            
            if (server == null) {
                Thread.yield();
                continue;
            }

            log.debug("service " + key + " on : " + server.getRpcServerAddress());
            return server;
//            if (server.getState() == ServiceState.AVAILABLE) {
//            	log.debug("service " + key + " on : " + server.getHostAddress());
//                return server;
//            }
//
//            // Next.
//            server = null;
        }

        return server;
	}
	
	private void sortByWeight(List<ServiceServer> weights) {
        Collections.sort(weights, weightComparator);
    }
	
	public static class WeightComparator implements Comparator<ServiceServer>{

		@Override
		public int compare(ServiceServer arg0, ServiceServer arg1) {
			return arg0.getWeights()>arg1.getWeights() ? 1 : 0;
		}
		
	}

	
}
