package com.goldgov.origin.modules.organization.service.impl;

import java.util.List;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldgov.origin.core.discovery.rpc.ObjectConverter.Utils;
import com.goldgov.origin.core.discovery.rpc.ResultSetUtils;
import com.goldgov.origin.core.discovery.rpc.RpcService;
import com.goldgov.origin.modules.organization.api.RpcOrganization;
import com.goldgov.origin.modules.organization.api.RpcOrganizationPost;
import com.goldgov.origin.modules.organization.api.RpcOrganizationQuery;
import com.goldgov.origin.modules.organization.api.RpcOrganizationService;
import com.goldgov.origin.modules.organization.service.Organization;
import com.goldgov.origin.modules.organization.service.OrganizationPost;
import com.goldgov.origin.modules.organization.service.OrganizationService;
import com.goldgov.origin.modules.organization.service.ProxyOrganizationPost;
import com.goldgov.origin.modules.organization.service.ProxyOrganizationQuery;

/**
 * 组织机构管理
 * @author LiuHG
 */
@RpcService
public class RpcOrganizationServiceImpl implements RpcOrganizationService.Iface{

	@Autowired
	private OrganizationService organizationService;
	
	private OrganizationConverter orgConverter = new OrganizationConverter();
//	private OrganizationUserConverter orgUserConverter = new OrganizationUserConverter();
	private OrganizationPostConverter orgPostConverter = new OrganizationPostConverter();
//	private PostUserConverter postUserConverter = new PostUserConverter();
	
	@Override
	public String addOrganization(RpcOrganization organization) throws TException {
		organizationService.addOrganization(orgConverter.fromRpcObject(organization));
		return organization.getOrgID();
	}

	@Override
	public void updateOrganization(RpcOrganization organization) throws TException {
		organizationService.updateOrganization(orgConverter.fromRpcObject(organization));
	}

	@Override
	public void deleteOrganization(List<String> ids) throws TException {
		organizationService.deleteOrganization(Utils.listToArray(ids));
	}
	
	@Override
	public RpcOrganization getOrganization(String id) throws TException {
		Organization organization = organizationService.getOrganization(id);
		return orgConverter.toRpcObject(organization);
	}

	@Override
	public RpcOrganizationQuery listOrganization(RpcOrganizationQuery query) throws TException {
		ProxyOrganizationQuery proxyQuery=new ProxyOrganizationQuery(query);
		List<Organization> rpcList = organizationService.listOrganization(proxyQuery);
		query.setResultList(ResultSetUtils.convertToRpc(rpcList, orgConverter));
		return query;
	}

	@Override
	public void addOrgUser(String orgID, List<String> users) throws TException {
		organizationService.addOrgUser(orgID,Utils.listToArray(users));
	}

	@Override
	public void deleteOrgUser(List<String> orgUserID) throws TException {
		organizationService.deleteOrgUser(Utils.listToArray(orgUserID));
	}

	@Override
	public void deleteOrgUserByUser(String orgID, List<String> users) throws TException {
		organizationService.deleteOrgUserByUser(orgID, Utils.listToArray(users));
	}

	@Override
	public void addOrganizationPost(RpcOrganizationPost post) throws TException {
		organizationService.addOrganizationPost(new ProxyOrganizationPost(post));
	}

	@Override
	public void deleteOrganizationPost(List<String> ids) throws TException {
		organizationService.deleteOrganizationPost(Utils.listToArray(ids));
	}

	@Override
	public void updateOrganizationPost(RpcOrganizationPost post) throws TException {
		organizationService.updateOrganizationPost(new ProxyOrganizationPost(post));
	}

	@Override
	public List<RpcOrganizationPost> listOrganizationPost(String orgID) throws TException {
		List<OrganizationPost> listOrganizationPost = organizationService.listOrganizationPost(orgID);
		return ResultSetUtils.convertToRpc(listOrganizationPost, orgPostConverter);
	}

	@Override
	public RpcOrganizationPost getOrganizationPost(String id) throws TException {
		OrganizationPost organizationPost = organizationService.getOrganizationPost(id);
		return new ProxyOrganizationPost(organizationPost).toRpcObject();
	}

	@Override
	public void addPostUser(String postID, List<String> users) throws TException {
		organizationService.addPostUser(postID, Utils.listToArray(users));
	}

	@Override
	public void deletePostUser(List<String> postUserID) throws TException {
		organizationService.deletePostUser(Utils.listToArray(postUserID));
	}

	@Override
	public void deletePostUserByUser(String postID, List<String> users) throws TException {
		organizationService.deletePostUserByUser(postID, Utils.listToArray(users));
	}

}
