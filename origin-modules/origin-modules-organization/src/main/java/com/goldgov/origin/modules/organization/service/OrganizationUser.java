package com.goldgov.origin.modules.organization.service;

public class OrganizationUser {

	private String orgUserID;
	private String orgUser;
	private Organization organization;
	
	public String getOrgUserID() {
		return orgUserID;
	}
	public void setOrgUserID(String orgUserID) {
		this.orgUserID = orgUserID;
	}
	public String getOrgUser() {
		return orgUser;
	}
	public void setOrgUser(String orgUser) {
		this.orgUser = orgUser;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	
}
