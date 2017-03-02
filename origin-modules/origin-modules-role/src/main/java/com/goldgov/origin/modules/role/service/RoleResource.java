package com.goldgov.origin.modules.role.service;

public class RoleResource {

	private Integer roleResourceID;
//	private Resource resource;
	private Integer operateID;
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
	public Integer getOperateID() {
		return operateID;
	}
	public void setOperateID(Integer operateID) {
		this.operateID = operateID;
	}
}
