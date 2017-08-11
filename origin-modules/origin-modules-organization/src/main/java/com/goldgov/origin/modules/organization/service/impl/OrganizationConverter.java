package com.goldgov.origin.modules.organization.service.impl;

import com.goldgov.origin.core.discovery.rpc.ObjectConverter;
import com.goldgov.origin.modules.organization.api.RpcOrganization;
import com.goldgov.origin.modules.organization.service.Organization;
import com.goldgov.origin.modules.organization.service.ProxyOrganization;

/**
 * 组织机构管理
 * @author LiuHG
 */
public class OrganizationConverter implements ObjectConverter<Organization,RpcOrganization>{

	@Override
	public RpcOrganization toRpcObject(Organization obj) {
		return new ProxyOrganization(obj).toRpcObject();
	}

	@Override
	public Organization fromRpcObject(RpcOrganization rpcObj) {
		return new ProxyOrganization(rpcObj);
	}

}
