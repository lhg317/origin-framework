package com.goldgov.origin.modules.role.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldgov.origin.core.discovery.rpc.RpcService;
import com.goldgov.origin.modules.role.api.RpcRole;
import com.goldgov.origin.modules.role.api.RpcRoleQuery;
import com.goldgov.origin.modules.role.api.RpcRoleResource;
import com.goldgov.origin.modules.role.api.RpcRoleService;
import com.goldgov.origin.modules.role.service.Role;
import com.goldgov.origin.modules.role.service.RoleResource;
import com.goldgov.origin.modules.role.service.RoleService;

@RpcService
public class RpcRoleServiceImpl implements RpcRoleService.Iface{

	@Autowired
	private RoleService roleService;
	
	@Override
	public void addRole(RpcRole role) {
		roleService.addRole(new ProxyRole(role));
	}

	@Override
	public void deleteRoles(List<Integer> ids) throws TException {
		roleService.deleteRoles(ids.toArray(new Integer[0]));
	}

	@Override
	public void updateRole(RpcRole role) throws TException {
		roleService.updateRole(new ProxyRole(role));
	}

	@Override
	public RpcRole findRoleByID(int roleID) throws TException {
		Role role = roleService.findRoleByID(roleID);
		return new ProxyRole(role).toRpcRole();
	}

	@Override
	public RpcRoleQuery findRoles(RpcRoleQuery query) throws TException {
		List<Role> roleList = roleService.findRoles(new ProxyRoleQuery(query));
		List<RpcRole> resultList = new ArrayList<>();
		for (Role role : roleList) {
			resultList.add(new ProxyRole(role).toRpcRole());
		}
		query.setResultList(resultList);
		return query;
	}

	@Override
	public void saveRoleResources(int roleID, List<String> resourceOperate) throws TException {
		roleService.saveRoleResources(roleID, resourceOperate.toArray(new String[0]));
	}

	@Override
	public void saveRoleObjects(int roleID, List<String> roleObject) throws TException {
		roleService.saveRoleObjects(roleID, roleObject.toArray(new String[0]));
	}

	@Override
	public List<RpcRole> findRolesByObject(String roleObject) throws TException {
		List<Role> roleList = roleService.findRolesByObject(roleObject);
		List<RpcRole> resultList = new ArrayList<>();
		for (Role role : roleList) {
			resultList.add(new ProxyRole(role).toRpcRole());
		}
		return resultList;
	}

	@Override
	public List<RpcRoleResource> findRoleResourcesByObject(String roleObject) throws TException {
		List<RoleResource> roleResourceList = roleService.findRoleResourcesByObject(roleObject);
		List<RpcRoleResource> resultList = new ArrayList<>();
		for (RoleResource roleResource : roleResourceList) {
			RpcRoleResource rpcRoleResource = new RpcRoleResource();
			rpcRoleResource.setRoleResourceID(roleResource.getRoleResourceID());
			rpcRoleResource.setRoleID(roleResource.getRoleID());
			rpcRoleResource.setResourceOperate(roleResource.getResourceOperate());
			resultList.add(rpcRoleResource);
		}
		return resultList;
	}

	@Override
	public void initRoleResourcesMap() throws TException {
		roleService.initRoleResourcesMap();
	}

	@Override
	public List<Map<String, String>> getRoleResourcesMap() throws TException {
		return roleService.getRoleResourcesMap();
	}

}
