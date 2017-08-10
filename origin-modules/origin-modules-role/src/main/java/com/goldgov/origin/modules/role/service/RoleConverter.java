package com.goldgov.origin.modules.role.service;

import com.goldgov.origin.core.discovery.rpc.ObjectConverter;
import com.goldgov.origin.modules.role.api.RpcRole;

public class RoleConverter implements ObjectConverter<Role, RpcRole>{

	@Override
	public RpcRole toRpcObject(Role obj) {
		return new ProxyRole(obj).toRpcObject();
	}

	@Override
	public Role fromRpcObject(RpcRole rpcObj) {
		return new ProxyRole(rpcObj);
	}

}
