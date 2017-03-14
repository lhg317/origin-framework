package com.goldgov.origin.modules.role.service;

import java.util.List;

public interface RoleService {

	public void addRole(Role role);
	
	public void saveRoleResources(Integer roleID,String[] resourceOperate);
	
	public void saveRoleObjects(Integer roleID,String[] roleObject);
	
	public void updateRole(Role role);
	
	public void deleteRoles(Integer[] ids);
	
	public void deleteRoleResources(Integer[] ids);
	
	public Role findRoleByID(Integer id);
	
	public List<Role> findRoles(RoleQuery<Role> roleQuery);
	
	public RoleResource findRoleResourceByCode(String code);

	public List<Role> findRolesByObject(String roleObject);
	public List<RoleResource> findRoleResourcesByObject(String roleObject);
	
	public void initRoleResourcesMap();
	
}
