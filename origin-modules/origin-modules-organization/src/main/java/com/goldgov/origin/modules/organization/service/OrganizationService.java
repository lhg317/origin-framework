package com.goldgov.origin.modules.organization.service;

import java.util.List;

/**
 * 组织机构管理
 * @author LiuHG
 *
 */
public interface OrganizationService {
	
	void addOrganization(Organization organization);
	
	void deleteOrganization(String[] ids);
	
	void updateOrganization(Organization organization);
	
	List<Organization> listOrganization(OrganizationQuery query);
	
	List<Organization> listOrganizationByUser(String user);
	
	Organization getOrganization(String id);
	
	void addOrgUser(String orgID,String[] users);
	
	void deleteOrgUser(String[] orgUserID);
	
	void deleteOrgUserByUser(String orgID,String[] users);
	
	void addOrganizationPost(OrganizationPost post);
	
	void deleteOrganizationPost(String[] ids);
	
	void updateOrganizationPost(OrganizationPost post);
	
	List<OrganizationPost> listOrganizationPost(String orgID);
	
	OrganizationPost getOrganizationPost(String id);
	
	void addPostUser(String postID,String[] users);
	
	void deletePostUser(String[] postUserID);
	
	void deletePostUserByUser(String postID,String[] users);

}
