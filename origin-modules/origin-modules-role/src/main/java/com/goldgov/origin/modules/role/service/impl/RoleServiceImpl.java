package com.goldgov.origin.modules.role.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldgov.origin.core.cache.CacheHolder;
import com.goldgov.origin.modules.role.dao.RoleDao;
import com.goldgov.origin.modules.role.service.Role;
import com.goldgov.origin.modules.role.service.RoleQuery;
import com.goldgov.origin.modules.role.service.RoleResource;
import com.goldgov.origin.modules.role.service.RoleService;
import com.goldgov.origin.security.resource.ResourceConstants;

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
	public void saveRoleResource(String roleID,String[] resourceOperates) {
		roleDao.deleteRoleResourceByRoleID(roleID);
		roleDao.addRoleResource(roleID,resourceOperates);
		
		refreshRoleResourceCache();
	}
	
	@Override
	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	@Override
	public void deleteRole(String[] ids) {
		roleDao.deleteRoles(ids);
	}

	@Override
	public void deleteRoleResource(String[] ids) {
		roleDao.deleteRoleResource(ids);
	}
	
	@Override
	public Role getRole(String id) {
		return roleDao.getRole(id);
	}

	@Override
	public List<Role> listRole(RoleQuery<Role> roleQuery) {
		return roleDao.listRole(roleQuery);
	}


	@Override
	public List<RoleResource> listRoleResourceByObject(String roleObject) {
		return roleDao.listRoleResourceByObject(roleObject);
	}

	@Override
	public List<Role> listRoleByObject(String roleObject) {
		return roleDao.listRoleByObject(roleObject);
	}
	
	@Override
	@Transactional
	public void saveRoleObject(String roleID, String[] roleObject) {
		roleDao.deleteRoleObjectByRoleID(roleID);
		roleDao.addRoleObject(roleID, roleObject);
	}

	@Override
	public Map<String,List<String>> getRoleResourceMap() {
		List<Map<String, String>> roleResourceMapList = roleDao.listRoleResourceMap();
		Map<String,List<String>> roleResourceMap = new HashMap<>();
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
		return roleResourceMap;
	}

	@Override
	public void refreshRoleResourceCache() {
		Map<String, List<String>> roleResourceMap = getRoleResourceMap();
		CacheHolder.put(ResourceConstants.CACHE_CODE_ROLE_RESOURCE_MAPPING, roleResourceMap);
	}

}
