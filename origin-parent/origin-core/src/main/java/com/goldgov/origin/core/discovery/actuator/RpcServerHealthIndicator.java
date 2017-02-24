package com.goldgov.origin.core.discovery.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.boot.actuate.health.HealthIndicator;

import com.goldgov.origin.core.discovery.rpc.ThriftRpcServer;

public class RpcServerHealthIndicator extends AbstractHealthIndicator implements HealthIndicator{

	@Autowired(required=false)
	private ThriftRpcServer thriftRpcServer;

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		if(thriftRpcServer.getRpcServiceList().size() == 0 && !thriftRpcServer.isServing()){
			builder.up()
			.withDetail("port", thriftRpcServer.getPort())
			.withDetail("serviceNum", 0)
			.withDetail("serverStatus", "No service, the server does not need to start.");
			return;
		}
		if(thriftRpcServer != null && thriftRpcServer.isServing()){
			builder.up()
			.withDetail("port", thriftRpcServer.getPort())
			.withDetail("serviceNum", thriftRpcServer.getRpcServiceList().size())
			.withDetail("serverStatus", "Server is serving.");
		}else{
			builder.down();
		}
	}


}
