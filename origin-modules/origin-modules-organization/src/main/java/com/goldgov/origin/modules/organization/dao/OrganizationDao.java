package com.goldgov.origin.modules.organization.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldgov.origin.core.dao.Mapper;
import com.goldgov.origin.modules.organization.service.Organization;
import com.goldgov.origin.modules.organization.service.OrganizationPost;
import com.goldgov.origin.modules.organization.service.OrganizationQuery;

/**
 * 组织机构管理
 * @author LiuHG
 */
@Mapper
public interface OrganizationDao {
	
	void addOrganization(Organization organization);
	
	void deleteOrganization(@Param("ids")String[] ids);
	
	void updateOrganization(Organization organization);
	
	List<Organization> listOrganization(@Param("query")OrganizationQuery query);
	
	List<Organization> listOrganizationByUser(@Param("user") String user);
	
	Organization getOrganization(@Param("id")String id);
	
	Integer getMaxNodeValue();
	
	String getNodePath(@Param("orgID")String orgID);
	
	void addOrgUser(@Param("orgID")String orgID,@Param("users")String[] users);
	
	void deleteOrgUser(@Param("orgUserID")String[] orgUserID);
	
	void deleteOrgUserByUser(@Param("orgID")String orgID,@Param("users") String[] users);
	
	void addOrganizationPost(OrganizationPost post);
	
	void deleteOrganizationPost(String[] ids);
	
	void updateOrganizationPost(OrganizationPost post);
	
	List<OrganizationPost> listOrganizationPost(String orgID);
	
	OrganizationPost getOrganizationPost(String id);
	
	void addPostUser(@Param("postID")String postID,@Param("users")String[] users);
	
	void deletePostUser(String[] postUserID);
	
	void deletePostUserByUser(@Param("postID")String postID,@Param("users")String[] users);
	
	
}