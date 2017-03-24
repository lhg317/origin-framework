package com.goldgov.origin.modules.role.service;

import java.util.List;
import java.util.Map;

public interface RoleService {

	public void addRole(Role role);
	
	void saveRoleResources(Integer roleID,String[] resourceOperate);
	
	void saveRoleObjects(Integer roleID,String[] roleObject);
	
	void updateRole(Role role);
	
	void deleteRoles(Integer[] ids);
	
	void deleteRoleResources(Integer[] ids);
	
	Role findRoleByID(Integer id);
	
	List<Role> findRoles(RoleQuery<Role> roleQuery);
	
	RoleResource findRoleResourceByCode(String code);

	List<Role> findRolesByObject(String roleObject);
	List<RoleResource> findRoleResourcesByObject(String roleObject);
	
	void initRoleResourcesMap();

	List<Map<String, String>> getRoleResourcesMap();
	
}
