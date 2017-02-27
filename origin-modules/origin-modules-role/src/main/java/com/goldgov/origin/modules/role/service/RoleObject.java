package com.goldgov.origin.modules.role.service;

public class RoleObject {

//	public static final int OBJECT_TYPE_USER = 1;//用户资源
//	public static final int OBJECT_TYPE_ORGANIZATION = 2;//机构资源
	
	private Integer roleObjectID;
	private Integer objectType;
	private String objectID;
	private String objectName;
	private Role role;
	public Integer getRoleObjectID() {
		return roleObjectID;
	}
	public void setRoleObjectID(Integer roleObjectID) {
		this.roleObjectID = roleObjectID;
	}
	public Integer getObjectType() {
		return objectType;
	}
	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}
	public String getObjectID() {
		return objectID;
	}
	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
