package com.goldgov.origin.modules.role.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldgov.origin.core.cache.CacheHolder;
import com.goldgov.origin.modules.role.RoleConstants;
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
	public void saveRoleResources(Integer roleID,String[] resourceOperates) {
		roleDao.deleteRoleResourceByRoleID(roleID);
		roleDao.addRoleResource(roleID,resourceOperates);
		
		initRoleResourcesMap();
	}
	
	@Override
	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	@Override
	public void deleteRoles(Integer[] ids) {
		roleDao.deleteRoles(ids);
	}

	@Override
	public void deleteRoleResources(Integer[] ids) {
		roleDao.deleteRoleResources(ids);
	}
	
	@Override
	public Role findRoleByID(Integer id) {
		return roleDao.findRoleByID(id);
	}

	@Override
	public List<Role> findRoles(RoleQuery<Role> roleQuery) {
		return roleDao.findRoles(roleQuery);
	}

	@Override
	public RoleResource findRoleResourceByCode(String code) {
		return roleDao.findRoleResource(code);
	}

	@Override
	public List<RoleResource> findRoleResourcesByObject(String roleObject) {
		return roleDao.findRoleResourcesByObject(roleObject);
	}

	@Override
	public List<Role> findRolesByObject(String roleObject) {
		return roleDao.findRolesByObject(roleObject);
	}
	
	@Override
	@Transactional
	public void saveRoleObjects(Integer roleID, String[] roleObject) {
		roleDao.deleteRoleObjectByRoleID(roleID);
		roleDao.addRoleObject(roleID, roleObject);
	}

//	@Override
//	public List<Map<String, String>> getRoleResourcesMap() {
//		return roleDao.getRoleResourcesMap();
//	}

	@Override
	public void initRoleResourcesMap() {
		Map<String,List<String>> roleResourceMap = new HashMap<>();
		List<Map<String, String>> roleResourceMapList = roleDao.getRoleResourcesMap();
		for (Map<String, String> map : roleResourceMapList) {
			String roleCode = map.get("roleCode");
			String resourceOperate = map.get("resourceOperate");
			List<String> roleCodeList;
			if(roleResourceMap.containsKey(resourceOperate)){
				roleCodeList = roleResourceMap.get(resourceOperate);
			}else{
				roleCodeList= new ArrayList<>();
				roleResourceMap.put(resourceOperate, roleCodeList);
			}
			roleCodeList.add(roleCode);
		}
		CacheHolder.put(RoleConstants.CACHE_CODE_ROLE_RESOURCE_MAPPING, roleResourceMap);
	}

}
