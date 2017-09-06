package com.goldgov.origin.modules.role.service;

public class RoleObject {

	public static final String USER = "USER";
	public static final String GROUP = "GROUP";
	
	private String roleObjectID;
	private String roleObject;
	private String type;
	private String roleID;
	
	public String getRoleObjectID() {
		return roleObjectID;
	}
	public void setRoleObjectID(String roleObjectID) {
		this.roleObjectID = roleObjectID;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
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
