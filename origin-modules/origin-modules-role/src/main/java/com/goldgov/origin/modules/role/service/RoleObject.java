package com.goldgov.origin.modules.role.service;

public class RoleObject {

	public static final String USER = "USER";
	public static final String GROUP = "GROUP";
	
	private Integer roleObjectID;
	private String roleObject;
	private String type;
	private Integer roleID;
	
	public Integer getRoleObjectID() {
		return roleObjectID;
	}
	public void setRoleObjectID(Integer roleObjectID) {
		this.roleObjectID = roleObjectID;
	}
	public Integer getRoleID() {
		return roleID;
	}
	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}
	public String getRoleObject() {
		return roleObject;
	}
	public void setRoleObject(String roleObject) {
		this.roleObject = roleObject;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
