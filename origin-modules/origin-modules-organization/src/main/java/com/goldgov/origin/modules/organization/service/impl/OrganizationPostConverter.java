package com.goldgov.origin.modules.organization.service.impl;

import com.goldgov.origin.core.discovery.rpc.ObjectConverter;
import com.goldgov.origin.modules.organization.api.RpcOrganizationPost;
import com.goldgov.origin.modules.organization.service.OrganizationPost;
import com.goldgov.origin.modules.organization.service.ProxyOrganizationPost;

public class OrganizationPostConverter implements ObjectConverter<OrganizationPost,RpcOrganizationPost>{

	@Override
	public RpcOrganizationPost toRpcObject(OrganizationPost obj) {
		return new ProxyOrganizationPost(obj).toRpcObject();
	}

	@Override
	public OrganizationPost fromRpcObject(RpcOrganizationPost rpcObj) {
		return new ProxyOrganizationPost(rpcObj);
	}
}
