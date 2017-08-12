package com.goldgov.origin.modules.organization.service;

import java.util.List;

/**
 * 组织机构管理
 * @author LiuHG
 *
 */
public interface OrganizationService {
	
	public void addOrganization(Organization organization);
	
	public void deleteOrganization(String[] ids);
	
	public void updateOrganization(Organization organization);
	
	public List<Organization> listOrganization(OrganizationQuery query);
	
	public Organization getOrganization(String id);
	
	public void addOrgUser(String orgID,String[] users);
	
	public void deleteOrgUser(String[] orgUserID);
	
	public void deleteOrgUserByUser(String orgID,String[] users);
	
	public void addOrganizationPost(OrganizationPost post);
	
	public void deleteOrganizationPost(String[] ids);
	
	public void updateOrganizationPost(OrganizationPost post);
	
	public List<OrganizationPost> listOrganizationPost(String orgID);
	
	public OrganizationPost getOrganizationPost(String id);
	
	public void addPostUser(String postID,String[] users);
	
	public void deletePostUser(String[] postUserID);
	
	public void deletePostUserByUser(String postID,String[] users);
	
}
