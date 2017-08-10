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
	
	public void addRoleResource(@Param("roleID") String roleID,@Param("resourceOperate") String[] resourceOperate);
	public void addRoleObject(@Param("roleID")String roleID, @Param("roleObject")String[] roleObject,@Param("roleObjectType")String roleObjectType);
	
	public void updateRole(Role role);
	
	public void deleteRoles(@Param("ids") String[] ids);
	
	public void deleteRoleResource(@Param("ids") String[] ids);
	
	public void deleteRoleResourceByRoleID(@Param("roleID") String roleID);
	
	public void deleteRoleObjectByRoleID(@Param("roleID") String roleID,@Param("roleObjectType") String roleObjectType);

	public Role getRole(String id);
	
	public List<Role> listRole(RoleQuery<Role> query);
	
	public List<RoleResource> listRoleResourceByObject(@Param("roleObject") String roleObject);

	public List<Role> listRoleByObject(@Param("roleObject") String roleObject);
	
	public List<Map<String,String>> listRoleResourceMap();

	public List<RoleResource> listRoleResourceByRoleID(@Param("roleID") String roleID);


}
