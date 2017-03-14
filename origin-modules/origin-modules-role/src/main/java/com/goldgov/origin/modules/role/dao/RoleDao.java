package com.goldgov.origin.modules.role.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.goldgov.origin.core.dao.Mapper;
import com.goldgov.origin.modules.role.service.Role;
import com.goldgov.origin.modules.role.service.RoleQuery;
import com.goldgov.origin.modules.role.service.RoleResource;

@Mapper("roleDao")
public interface RoleDao {

	public void addRole(Role role);
	
	public void addRoleResource(@Param("roleID") Integer roleID,@Param("resourceOperate") String[] resourceOperate);
	public void addRoleObject(@Param("roleID")Integer roleID, @Param("roleObject")String[] roleObject);
	
	public void updateRole(Role role);
	
	public void deleteRoles(@Param("ids") Integer[] ids);
	
	public void deleteRoleResources(@Param("ids") Integer[] ids);
	
	public void deleteRoleResourceByRoleID(@Param("roleID") Integer roleID);
	public void deleteRoleObjectByRoleID(@Param("roleID") Integer roleID);

	public Role findRoleByID(Integer id);
	
	public List<Role> findRoles(RoleQuery<Role> query);
	
	public RoleResource findRoleResource(String code);
	
	public List<RoleResource> findRoleResourcesByObject(@Param("roleObject") String roleObject);

	public List<Role> findRolesByObject(@Param("roleObject") String roleObject);
	
	public List<Map<String,String>> getRoleResourcesMap();


}
