package com.goldgov.origin.modules.organization.service;

import com.goldgov.origin.core.service.Query;

public class OrganizationUserQuery extends Query<OrganizationUser>{

	private String searchOrgID;

	public String getSearchOrgID() {
		return searchOrgID;
	}

	public void setSearchOrgID(String searchOrgID) {
		this.searchOrgID = searchOrgID;
	}
	
	
}
