package com.goldgov.origin.core.discovery.actuator.metrics;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;

import com.goldgov.origin.core.discovery.rpc.ServiceProviderCenter;
import com.goldgov.origin.core.discovery.rpc.pool.SocketProvider;

public class RpcPublicMetrics implements PublicMetrics{

	@Autowired
	private ServiceProviderCenter providerCenter;
	
	@Override
	public Collection<Metric<?>> metrics() {
		Collection<Metric<?>> result = new LinkedHashSet<Metric<?>>();
		Map<String, SocketProvider> allSocketProvider = providerCenter.getAllSocketProvider();
		Collection<SocketProvider> socketCollection = allSocketProvider.values();
		for (SocketProvider socketProvider : socketCollection) {
			String host = socketProvider.getServiceIP() + ":" + socketProvider.getServicePort();
			result.add(new Metric<Integer>("rpc.pool.num-active."+host, socketProvider.getNumActive()));
			result.add(new Metric<Integer>("rpc.pool.max-total."+host, socketProvider.getMaxTotal()));
		}
		return result;
	}

}
