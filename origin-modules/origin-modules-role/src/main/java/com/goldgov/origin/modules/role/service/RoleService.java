package com.goldgov.origin.modules.role.service;

import java.util.List;
import java.util.Map;

public interface RoleService {

	public void addRole(Role role);
	
	public void saveRoleResources(Integer roleID,String[] resourceOperate);
	
	public void saveRoleObjects(Integer roleID,String[] roleObject);
	
	public void updateRole(Role role);
	
	public void deleteRoles(Integer[] ids);
	
	public void deleteRoleResource(Integer[] ids);
	
	public Role findRole(Integer id);
	
	public List<Role> findRoleListByPage(RoleQuery<Role> roleQuery);
	
	public RoleResource findRoleResource(String code);

	public List<Role> findRoleList(String roleObject);
	public List<RoleResource> findRoleResourceList(String roleObject);
	
	public List<Map<String, String>> findRoleResourceMap();
	
}
