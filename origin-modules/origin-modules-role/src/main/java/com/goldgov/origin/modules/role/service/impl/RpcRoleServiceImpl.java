package com.goldgov.origin.modules.role.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldgov.origin.core.discovery.rpc.RpcService;
import com.goldgov.origin.modules.role.api.ProxyRole;
import com.goldgov.origin.modules.role.api.ProxyRoleQuery;
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
	public void deleteRole(List<Integer> ids) throws TException {
		roleService.deleteRole(ids.toArray(new Integer[0]));
	}

	@Override
	public void updateRole(RpcRole role) throws TException {
		roleService.updateRole(new ProxyRole(role));
	}

	@Override
	public RpcRole findRole(int roleID) throws TException {
		Role role = roleService.findRole(roleID);
		return new ProxyRole(role).toRpcRole();
	}

	@Override
	public RpcRoleQuery findRoleList(RpcRoleQuery query) throws TException {
		List<Role> roleList = roleService.findRoleListByPage(new ProxyRoleQuery(query));
		List<RpcRole> resultList = new ArrayList<>();
		for (Role role : roleList) {
			resultList.add(new ProxyRole(role).toRpcRole());
		}
		query.setResultList(resultList);
		return query;
	}

	@Override
	public void saveRoleResource(int roleID, List<String> resourceOperate) throws TException {
		roleService.saveRoleResource(roleID, resourceOperate.toArray(new String[0]));
	}

	@Override
	public void saveRoleObject(int roleID, List<String> roleObject) throws TException {
		roleService.saveRoleObject(roleID, roleObject.toArray(new String[0]));
	}

	@Override
	public List<RpcRole> findRoleByObject(String roleObject) throws TException {
		List<Role> roleList = roleService.findRoleList(roleObject);
		List<RpcRole> resultList = new ArrayList<>();
		for (Role role : roleList) {
			resultList.add(new ProxyRole(role).toRpcRole());
		}
		return resultList;
	}

	@Override
	public List<RpcRoleResource> findRoleResourceByObject(String roleObject) throws TException {
		List<RoleResource> roleResourceList = roleService.findRoleResourceList(roleObject);
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
	public List<Map<String, String>> findRoleResourceMap() throws TException {
		return roleService.findRoleResourceMap();
	}

}
