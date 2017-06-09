package com.goldgov.origin.core.discovery.rpc;

public class ClassServiceNameGeneratorImpl implements ServiceNameGenerator{

	@Override
	public String generateServiceName(Class<?> rpcClass) {
		return rpcClass.getName();
	}

}
