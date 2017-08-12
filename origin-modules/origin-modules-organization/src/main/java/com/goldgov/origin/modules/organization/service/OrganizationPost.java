package com.goldgov.origin.modules.organization.service;

public class OrganizationPost {

	private String orgPostID;
	private String postName;
	private String postCode;
	private Organization organization;
	
	public String getOrgPostID() {
		return orgPostID;
	}
	public void setOrgPostID(String orgPostID) {
		this.orgPostID = orgPostID;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	
}
