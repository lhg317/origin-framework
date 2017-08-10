package com.goldgov.origin.modules.role.service;

import com.goldgov.origin.core.discovery.rpc.ObjectConverter;
import com.goldgov.origin.modules.role.api.RpcRoleResource;

public class RoleResourceConverter implements ObjectConverter<RoleResource, RpcRoleResource> {

	@Override
	public RpcRoleResource toRpcObject(RoleResource obj) {
		return new ProxyRoleResource(obj).toRpcObject();
	}

	@Override
	public RoleResource fromRpcObject(RpcRoleResource rpcObj) {
		return new ProxyRoleResource(rpcObj);
	}

}
