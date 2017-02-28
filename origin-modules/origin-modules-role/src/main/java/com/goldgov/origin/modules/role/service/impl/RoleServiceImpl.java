package com.goldgov.origin.modules.role.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldgov.origin.modules.role.dao.RoleDao;
import com.goldgov.origin.modules.role.service.Role;
import com.goldgov.origin.modules.role.service.RoleObject;
import com.goldgov.origin.modules.role.service.RoleQuery;
import com.goldgov.origin.modules.role.service.RoleResource;
import com.goldgov.origin.modules.role.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public void addRole(Role role) {
		roleDao.addRole(role);
	}

	@Override
	public void addRoleResource(RoleResource roleResource) {
		roleDao.addRoleResource(roleResource);
	}
	
	public void addRole(RoleObject roleObject){
		//TODO 
	}

	@Override
	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	@Override
	public void deleteRole(Integer[] ids) {
		roleDao.deleteRole(ids);
	}

	@Override
	public void deleteRoleResource(Integer[] ids) {
		roleDao.deleteRoleResource(ids);
	}
	
	public void deleteRoleObject(Integer[] ids){
		//TODO 
	}

	@Override
	public Role findRole(Integer id) {
		return roleDao.findRole(id);
	}

	@Override
	public List<Role> findRoleListByPage(RoleQuery roleQuery) {
		return roleDao.findRoleListByPage(roleQuery);
	}

	@Override
	public RoleResource findRoleResource(String code) {
		return roleDao.findRoleResource(code);
	}

	@Override
	public List<RoleResource> findRoleResourceList(String objectID) {
		return roleDao.findRoleResourceList(objectID);
	}
}
