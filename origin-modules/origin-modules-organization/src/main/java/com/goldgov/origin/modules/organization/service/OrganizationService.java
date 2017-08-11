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
	
}
