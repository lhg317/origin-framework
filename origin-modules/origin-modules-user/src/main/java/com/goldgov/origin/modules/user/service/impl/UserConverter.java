package com.goldgov.origin.modules.user.service.impl;

import com.goldgov.origin.core.discovery.rpc.ObjectConverter;
import com.goldgov.origin.modules.user.api.RpcUser;
import com.goldgov.origin.modules.user.service.User;

public class UserConverter implements ObjectConverter<User,RpcUser>{

	@Override
	public RpcUser toRpcObject(User obj) {
		return new ProxyUser(obj).toRpcUser();
	}

	@Override
	public User fromRpcObject(RpcUser rpcObj) {
		return new ProxyUser(rpcObj);
	}

}
