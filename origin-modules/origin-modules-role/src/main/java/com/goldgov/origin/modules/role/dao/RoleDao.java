package com.goldgov.origin.modules.role.dao;

import java.util.List;

import com.goldgov.origin.core.dao.Mapper;
import com.goldgov.origin.modules.role.service.Role;
import com.goldgov.origin.modules.role.service.RoleQuery;
import com.goldgov.origin.modules.role.service.RoleResource;

@Mapper("roleDao")
public interface RoleDao {

	public void addRole(Role role);
	
	public void addRoleResource(RoleResource roleResource);
	
	public void updateRole(Role role);
	
	public void deleteRole(Integer[] ids);
	
	public void deleteRoleResource(Integer[] ids);

	public Role findRole(Integer id);
	
	public List<Role> findRoleListByPage(RoleQuery query);
	
	public RoleResource findRoleResource(String code);
	
	public List<RoleResource> findRoleResourceList(String objectID);


}
