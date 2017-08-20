package com.goldgov.origin.modules.organization.service;

/**
 * 岗位用户
 * @author LiuHG
 * @version 1.0
 */
public class PostUser {

	private String postUserID;
	private OrganizationPost orgPost;
	private OrganizationUser orgUser;
	
	public String getPostUserID() {
		return postUserID;
	}
	public void setPostUserID(String postUserID) {
		this.postUserID = postUserID;
	}
	public OrganizationPost getOrgPost() {
		return orgPost;
	}
	public void setOrgPost(OrganizationPost orgPost) {
		this.orgPost = orgPost;
	}
	public OrganizationUser getOrgUser() {
		return orgUser;
	}
	public void setOrgUser(OrganizationUser orgUser) {
		this.orgUser = orgUser;
	}
	
}
