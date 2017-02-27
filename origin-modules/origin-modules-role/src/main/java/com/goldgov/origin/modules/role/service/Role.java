package com.goldgov.origin.modules.role.service;

public class Role {

	private Integer roleID;
	private String roleName;
	private String roleCode;
	private Integer roleType;
	private Integer isEnable;
	private String description;
	
	public Integer getRoleID() {
		return roleID;
	}
	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public Integer getRoleType() {
		return roleType;
	}
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
