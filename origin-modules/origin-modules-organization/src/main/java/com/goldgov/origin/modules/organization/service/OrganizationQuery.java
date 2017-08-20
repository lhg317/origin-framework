package com.goldgov.origin.modules.organization.service;

import com.goldgov.origin.core.service.Query;

/**
 * 组织机构管理
 * @author LiuHG
 */
public class OrganizationQuery extends Query<Organization>{
	
	private String searchParentID;

	public String getSearchParentID() {
		return searchParentID;
	}

	public void setSearchParentID(String searchParentID) {
		this.searchParentID = searchParentID;
	}
	
}
