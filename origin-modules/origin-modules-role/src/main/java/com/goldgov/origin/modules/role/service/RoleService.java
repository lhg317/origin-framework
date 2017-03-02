package com.goldgov.origin.modules.role.service;

import java.util.List;

public interface RoleService {

	public void addRole(Role role);
	
	public void addRoleResource(RoleResource roleResource);
	
	public void updateRole(Role role);
	
	public void deleteRole(Integer[] ids);
	
	public void deleteRoleResource(Integer[] ids);
	
	public Role findRole(Integer id);
	
	public List<Role> findRoleListByPage(RoleQuery roleQuery);
	
	public RoleResource findRoleResource(String code);

	public List<RoleResource> findRoleResourceList(String objectID);
	
}
