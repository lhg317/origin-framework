package com.goldgov.origin.modules.role.service;

import com.goldgov.origin.modules.resource.service.ResourceOperate;


public class RoleResource {

	private Integer roleResourceID;
//	private Resource resource;
	private ResourceOperate operate;
	private Integer roleID;
	
	public Integer getRoleResourceID() {
		return roleResourceID;
	}
	public void setRoleResourceID(Integer roleResourceID) {
		this.roleResourceID = roleResourceID;
	}
	public ResourceOperate getOperate() {
		return operate;
	}
	public void setOperate(ResourceOperate operate) {
		this.operate = operate;
	}
	public Integer getRoleID() {
		return roleID;
	}
	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}
	
}
