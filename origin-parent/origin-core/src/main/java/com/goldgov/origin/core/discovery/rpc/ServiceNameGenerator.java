package com.goldgov.origin.core.discovery.rpc;

public interface ServiceNameGenerator {

	public String generateServiceName(Class<?> rpcClass);
}
