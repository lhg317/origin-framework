package com.goldgov.origin.core.discovery.rpc;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import com.goldgov.origin.core.discovery.actuator.RpcServerHealthIndicator;
import com.goldgov.origin.core.discovery.actuator.RpcServiceEndPoint;
import com.goldgov.origin.core.discovery.actuator.metrics.RpcPublicMetrics;

public class RpcAutoConfiguration{

	@Bean("rpcServer")
	public ThriftRpcServer rpcServer(){
		ThriftRpcServer thriftRpcServer = new ThriftRpcServer();
		return thriftRpcServer;
	}
	
	@Bean
	public RpcScannerConfigurer rpcServiceScannerConfigurer(){
		RpcScannerConfigurer rpcServiceScannerConfigurer = new RpcScannerConfigurer();
		return rpcServiceScannerConfigurer;
	}
	
	@Bean
	public RpcServiceEndPoint rpcServiceEndPoint(){
		RpcServiceEndPoint rpcServiceEndPoint = new RpcServiceEndPoint();
		return rpcServiceEndPoint;
	}
	
	@Bean
	@ConditionalOnMissingBean(name="serviceNameGenerator")
	public ServiceNameGenerator serviceNameGenerator(){
		ServiceNameGenerator serviceNameGenerator = new ClassServiceNameGeneratorImpl();
		return serviceNameGenerator;
	}
	
	@Bean
	public RpcServerHealthIndicator rpcServerHealthIndicator(){
		RpcServerHealthIndicator rpcServerHealthIndicator = new RpcServerHealthIndicator();
		return rpcServerHealthIndicator;
	}
	
	@Bean
	public RpcPublicMetrics rpcPublicMetrics(){
		return new RpcPublicMetrics();
	}
	
}
