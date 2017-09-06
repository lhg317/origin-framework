package com.goldgov.origin.modules.role.service;

import com.goldgov.origin.core.discovery.rpc.ObjectConverter;
import com.goldgov.origin.modules.role.api.RpcRoleObject;

public class RoleObjectConverter implements ObjectConverter<RoleObject, RpcRoleObject> {

	@Override
	public RpcRoleObject toRpcObject(RoleObject obj) {
		return new ProxyRoleObject(obj).toRpcObject();
	}

	@Override
	public RoleObject fromRpcObject(RpcRoleObject rpcObj) {
		return new ProxyRoleObject(rpcObj);
	}

}
