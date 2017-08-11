package com.goldgov.origin.modules.organization.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.goldgov.origin.core.dao.Mapper;

import com.goldgov.origin.modules.organization.service.Organization;
import com.goldgov.origin.modules.organization.service.OrganizationQuery;

/**
 * 组织机构管理
 * @author LiuHG
 */
@Mapper
public interface OrganizationDao {
	
	public void addOrganization(Organization organization);
	
	public void deleteOrganization(@Param("ids")String[] ids);
	
	public void updateOrganization(Organization organization);
	
	public List<Organization> listOrganization(@Param("query")OrganizationQuery query);
	
	public Organization getOrganization(@Param("id")String id);
	
}