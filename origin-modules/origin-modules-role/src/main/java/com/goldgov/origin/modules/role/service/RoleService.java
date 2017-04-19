package com.goldgov.origin.modules.role.service;

import java.util.List;
import java.util.Map;

public interface RoleService {

	public void addRole(Role role);
	
	void saveRoleResource(String roleID,String[] resourceOperate);
	
	void saveRoleObject(String roleID,String[] roleObject);
	
	void updateRole(Role role);
	
	void deleteRole(String[] ids);
	
	void deleteRoleResource(String[] ids);
	
	Role getRole(String id);
	
	List<Role> listRole(RoleQuery<Role> roleQuery);
	
	List<RoleResource> listRoleResourceByObject(String roleObject);

	List<Role> listRoleByObject(String roleObject);
	List<Map<String, String>> listRoleResourceMap();
	
	void initRoleResourcesMap();

	
}
