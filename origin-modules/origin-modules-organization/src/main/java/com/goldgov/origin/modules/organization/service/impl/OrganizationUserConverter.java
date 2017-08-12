package com.goldgov.origin.modules.organization.service.impl;

import com.goldgov.origin.core.discovery.rpc.ObjectConverter;
import com.goldgov.origin.modules.organization.api.RpcOrganizationUser;
import com.goldgov.origin.modules.organization.service.OrganizationUser;
import com.goldgov.origin.modules.organization.service.ProxyOrganizationUser;

public class OrganizationUserConverter implements ObjectConverter<OrganizationUser,RpcOrganizationUser>{

	@Override
	public RpcOrganizationUser toRpcObject(OrganizationUser obj) {
		return new ProxyOrganizationUser(obj).toRpcObject();
	}

	@Override
	public OrganizationUser fromRpcObject(RpcOrganizationUser rpcObj) {
		return new ProxyOrganizationUser(rpcObj);
	}

}
