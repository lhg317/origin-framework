package com.goldgov.origin.modules.organization.service.impl;

import java.util.List;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldgov.origin.core.discovery.rpc.ObjectConverter.Utils;
import com.goldgov.origin.core.discovery.rpc.ResultSetUtils;
import com.goldgov.origin.core.discovery.rpc.RpcService;
import com.goldgov.origin.modules.organization.api.RpcOrganization;
import com.goldgov.origin.modules.organization.api.RpcOrganizationQuery;
import com.goldgov.origin.modules.organization.api.RpcOrganizationService;
import com.goldgov.origin.modules.organization.service.Organization;
import com.goldgov.origin.modules.organization.service.OrganizationService;
import com.goldgov.origin.modules.organization.service.ProxyOrganizationQuery;

/**
 * 组织机构管理
 * @author LiuHG
 */
@RpcService
public class RpcOrganizationServiceImpl implements RpcOrganizationService.Iface{

	@Autowired
	private OrganizationService organizationService;
	
	private OrganizationConverter organizationConverter = new OrganizationConverter();
	
	@Override
	public String addOrganization(RpcOrganization organization) throws TException {
		organizationService.addOrganization(organizationConverter.fromRpcObject(organization));
		return organization.getOrgID();
	}

	@Override
	public void updateOrganization(RpcOrganization organization) throws TException {
		organizationService.updateOrganization(organizationConverter.fromRpcObject(organization));
	}

	@Override
	public void deleteOrganization(List<String> ids) throws TException {
		organizationService.deleteOrganization(Utils.listToArray(ids));
	}
	
	@Override
	public RpcOrganization getOrganization(String id) throws TException {
		Organization organization = organizationService.getOrganization(id);
		return organizationConverter.toRpcObject(organization);
	}

	@Override
	public RpcOrganizationQuery listOrganization(RpcOrganizationQuery query) throws TException {
		ProxyOrganizationQuery proxyQuery=new ProxyOrganizationQuery(query);
		List<Organization> rpcList = organizationService.listOrganization(proxyQuery);
		query.setResultList(ResultSetUtils.convertToRpc(rpcList, organizationConverter));
		return query;
	}

}
