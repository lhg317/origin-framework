package com.goldgov.origin.modules.auth.service.impl;

import com.goldgov.origin.core.discovery.rpc.ObjectConverter;
import com.goldgov.origin.modules.auth.api.RpcAuthAccount;
import com.goldgov.origin.modules.auth.service.AuthAccount;

public class AuthAccountConverter implements ObjectConverter<AuthAccount,RpcAuthAccount>{

	@Override
	public RpcAuthAccount toRpcObject(AuthAccount obj) {
		return new ProxyAuthAccount(obj).toRpcObject();
	}

	@Override
	public AuthAccount fromRpcObject(RpcAuthAccount rpcObj) {
		return new ProxyAuthAccount(rpcObj);
	}

}
