package com.goldgov.origin.modules.organization.service.impl;

import com.goldgov.origin.core.discovery.rpc.ObjectConverter;
import com.goldgov.origin.modules.organization.api.RpcPostUser;
import com.goldgov.origin.modules.organization.service.PostUser;
import com.goldgov.origin.modules.organization.service.ProxyPostUser;

public class PostUserConverter implements ObjectConverter<PostUser,RpcPostUser>{

	@Override
	public RpcPostUser toRpcObject(PostUser obj) {
		return new ProxyPostUser(obj).toRpcObject();
	}

	@Override
	public PostUser fromRpcObject(RpcPostUser rpcObj) {
		return new ProxyPostUser(rpcObj);
	}

}
