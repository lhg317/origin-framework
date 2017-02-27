package com.goldgov.origin.modules.role.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldgov.origin.modules.role.dao.RoleDao;
import com.goldgov.origin.modules.role.service.Role;
import com.goldgov.origin.modules.role.service.RoleQuery;
import com.goldgov.origin.modules.role.service.RoleResource;
import com.goldgov.origin.modules.role.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Integer addRole(Role role) {
		roleDao.addRole(role);
		return role.getRoleID();
	}

	@Override
	public Integer addRoleResource(RoleResource roleResource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	@Override
	public void updateRoleResource(RoleResource roleResource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRoleByIDs(String[] ids) {
		roleDao.deleteRoleByIds(ids);
	}

	@Override
	public void deleteRoleResourceByIDs(String[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Role findRoleById(String id) {
		return roleDao.findRoleById(id);
	}

	@Override
	public List<Role> findRoleListByPage(RoleQuery roleQuery) {
		return roleDao.findRoleListByPage(roleQuery);
	}

	@Override
	public RoleResource findRoleResourceByUrl(String requestUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleResource> findRoleResourceListByUserID(String entityID) {
		// TODO Auto-generated method stub
		return null;
	}
}
