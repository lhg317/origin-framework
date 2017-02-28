package com.goldgov.origin.modules.role.service;

public class RoleObject {

	private Integer roleObjectID;
	private String objectID;
	private Role role;
	public Integer getRoleObjectID() {
		return roleObjectID;
	}
	public void setRoleObjectID(Integer roleObjectID) {
		this.roleObjectID = roleObjectID;
	}
	public String getObjectID() {
		return objectID;
	}
	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
