package com.goldgov.origin.core.discovery.rpc.pool;

import org.apache.thrift.transport.TTransport;

public interface SocketProvider {

	public TTransport getSocket();  
    
	public void release(TTransport socket);
	
	public void destroy();
	
	public int getNumActive();
	
	public int getMaxTotal();
	
}
