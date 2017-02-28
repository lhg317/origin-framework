package com.goldgov.origin.modules.role.service;

import com.goldgov.origin.modules.resource.service.ResourceOperate;


public class RoleResource {

	private String roleResourceID;
//	private Resource resource;
	private ResourceOperate operate;
	private Role role;
	
	public String getRoleResourceID() {
		return roleResourceID;
	}
	public void setRoleResourceID(String roleResourceID) {
		this.roleResourceID = roleResourceID;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public ResourceOperate getOperate() {
		return operate;
	}
	public void setOperate(ResourceOperate operate) {
		this.operate = operate;
	}
	
}
