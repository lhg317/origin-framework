package com.goldgov.origin.core.discovery.rpc;

import com.goldgov.origin.core.discovery.ServiceServer;

public class NoServiceInstance extends RpcServiceInstance{

	private static final long serialVersionUID = -7019139047215608525L;
	

	public NoServiceInstance(ServiceServer serviceServer) {
		super(null,serviceServer);
	}

}
