package com.goldgov.origin.core.discovery.rpc;

public interface ObjectConverter<T,R> {

	public R toRpcObject(T obj);
	public T fromRpcObject(R rpcObj);
}
