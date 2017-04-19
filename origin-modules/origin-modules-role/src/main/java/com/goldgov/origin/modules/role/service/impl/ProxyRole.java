package com.goldgov.origin.modules.role.service.impl;

import com.goldgov.origin.modules.role.api.RpcRole;
import com.goldgov.origin.modules.role.service.Role;

public class ProxyRole extends Role{

	private RpcRole role;
	
	public ProxyRole(){
		role = new RpcRole();
	}
	
	public ProxyRole(RpcRole role){
		this.role = role;
	}
	
	public ProxyRole(Role _role){
		this();
		if(_role == null){return;}
		setRoleID(_role.getRoleID());
		setRoleName(_role.getRoleName());
		setRoleCode(_role.getRoleCode());
		setDescription(_role.getDescription());
	}
	
	public String getRoleID() {
		return role.getRoleID();
	}
	public void setRoleID(String roleID) {
		role.setRoleID(roleID);
	}
	public String getRoleName() {
		return role.getRoleName();
	}
	public void setRoleName(String roleName) {
		role.setRoleName(roleName);
	}
	public String getRoleCode() {
		return role.getRoleCode();
	}
	public void setRoleCode(String roleCode) {
		role.setRoleCode(roleCode);
	}
	public String getDescription() {
		return role.getDescription();
	}
	public void setDescription(String description) {
		role.setDescription(description);
	}

	public RpcRole toRpcRole(){
		return role;
	}
	
}
