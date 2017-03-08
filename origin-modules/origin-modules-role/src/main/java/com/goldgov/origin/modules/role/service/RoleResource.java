package com.goldgov.origin.modules.role.service;

public class RoleResource {

	private Integer roleResourceID;
//	private Resource resource;
	private String resourceOperate;
	private Integer roleID;
	
	public Integer getRoleResourceID() {
		return roleResourceID;
	}
	public void setRoleResourceID(Integer roleResourceID) {
		this.roleResourceID = roleResourceID;
	}
	public Integer getRoleID() {
		return roleID;
	}
	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}
	public String getResourceOperate() {
		return resourceOperate;
	}
	public void setResourceOperate(String resourceOperate) {
		this.resourceOperate = resourceOperate;
	}
	
}
