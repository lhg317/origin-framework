package com.goldgov.origin.modules.role.service;

import java.util.List;

public interface RoleService {

	public Integer addRole(Role role);
	
	public Integer addRoleResource(RoleResource roleResource);
	
	public void updateRole(Role role);
	
	public void updateRoleResource(RoleResource roleResource);
	
	public void deleteRole(Integer[] ids);
	
	public void deleteRoleResource(Integer[] ids);
	
	public Role findRole(Integer id);
	
	public List<Role> findRoleListByPage(RoleQuery roleQuery);
	
	public RoleResource findRoleResourceByUrl(String requestUrl);

	public List<RoleResource> findRoleResourceListByUserID(String entityID);
	
}
