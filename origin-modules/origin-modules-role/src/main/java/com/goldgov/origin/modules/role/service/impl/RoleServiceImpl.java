package com.goldgov.origin.modules.role.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public void addRole(Role role) {
		roleDao.addRole(role);
	}

	@Override
	@Transactional
	public void saveRoleResource(Integer roleID,String[] resourceOperate) {
		roleDao.deleteRoleResourceByRoleID(roleID);
		roleDao.addRoleResource(roleID,resourceOperate);
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
	
	@Override
	public Role findRole(Integer id) {
		return roleDao.findRole(id);
	}

	@Override
	public List<Role> findRoleListByPage(RoleQuery<Role> roleQuery) {
		return roleDao.findRoleListByPage(roleQuery);
	}

	@Override
	public RoleResource findRoleResource(String code) {
		return roleDao.findRoleResource(code);
	}

	@Override
	public List<RoleResource> findRoleResourceList(String roleObject) {
		return roleDao.findRoleResourceList(roleObject);
	}

	@Override
	public List<Role> findRoleList(String roleObject) {
		return roleDao.findRoleList(roleObject);
	}
	
	@Override
	@Transactional
	public void saveRoleObject(Integer roleID, String[] roleObject) {
		roleDao.deleteRoleObjectByRoleID(roleID);
		roleDao.addRoleObject(roleID, roleObject);
	}

}
