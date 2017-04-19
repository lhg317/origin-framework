package com.goldgov.origin.modules.role.service;

public class RoleResource {

	private String roleResourceID;
//	private Resource resource;
	private String resourceOperate;
	private String roleID;
	
	public String getRoleResourceID() {
		return roleResourceID;
	}
	public void setRoleResourceID(String roleResourceID) {
		this.roleResourceID = roleResourceID;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getResourceOperate() {
		return resourceOperate;
	}
	public void setResourceOperate(String resourceOperate) {
		this.resourceOperate = resourceOperate;
	}
	
}
