package com.goldgov.origin.modules.role.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldgov.origin.core.discovery.rpc.ResultSetUtils;
import com.goldgov.origin.core.discovery.rpc.RpcService;
import com.goldgov.origin.modules.role.api.RpcRole;
import com.goldgov.origin.modules.role.api.RpcRoleObject;
import com.goldgov.origin.modules.role.api.RpcRoleQuery;
import com.goldgov.origin.modules.role.api.RpcRoleResource;
import com.goldgov.origin.modules.role.api.RpcRoleService;
import com.goldgov.origin.modules.role.service.ProxyRoleQuery;
import com.goldgov.origin.modules.role.service.Role;
import com.goldgov.origin.modules.role.service.RoleConverter;
import com.goldgov.origin.modules.role.service.RoleObject;
import com.goldgov.origin.modules.role.service.RoleObjectConverter;
import com.goldgov.origin.modules.role.service.RoleResource;
import com.goldgov.origin.modules.role.service.RoleResourceConverter;
import com.goldgov.origin.modules.role.service.RoleService;

@RpcService
public class RpcRoleServiceImpl implements RpcRoleService.Iface{

	@Autowired
	private RoleService roleService;
	
	private RoleConverter roleConverter = new RoleConverter();
	private RoleResourceConverter roleResourceConverter = new RoleResourceConverter();
	private RoleObjectConverter roleObjectConverter = new RoleObjectConverter();
	
	@Override
	public void addRole(RpcRole role) {
		roleService.addRole(roleConverter.fromRpcObject(role));
	}

	@Override
	public void deleteRole(List<String> ids) throws TException {
		roleService.deleteRole(ids.toArray(new String[0]));
	}

	@Override
	public void updateRole(RpcRole role) throws TException {
		roleService.updateRole(roleConverter.fromRpcObject(role));
	}

	@Override
	public RpcRole getRole(String roleID) throws TException {
		Role role = roleService.getRole(roleID);
		return roleConverter.toRpcObject(role);
	}

	@Override
	public RpcRoleQuery listRole(RpcRoleQuery query) throws TException {
		List<Role> roleList = roleService.listRole(new ProxyRoleQuery(query));
		query.setResultList(ResultSetUtils.convertToRpc(roleList, roleConverter));
		return query;
	}

	@Override
	public void saveRoleResource(String roleID, List<String> resourceOperate) throws TException {
		roleService.saveRoleResource(roleID, resourceOperate.toArray(new String[0]));
	}

	@Override
	public void saveRoleObject(String roleID, List<String> roleObject,String roleObjectType) throws TException {
		roleService.saveRoleObject(roleID, roleObject.toArray(new String[0]),roleObjectType);
	}

	@Override
	public List<RpcRole> listRoleByObject(String roleObject,String objectType) throws TException {
		List<Role> roleList = roleService.listRoleByObject(roleObject,objectType);
		List<RpcRole> resultList = ResultSetUtils.convertToRpc(roleList, roleConverter);
		return resultList;
	}

	@Override
	public List<RpcRoleResource> listRoleResourceByObject(String roleObject,String roleObjectType) throws TException {
		List<RoleResource> roleResourceList = roleService.listRoleResourceByObject(roleObject,roleObjectType);
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

//	@Override
//	public void refreshRoleResourceCache() throws TException {
////		roleService.refreshRoleResourceCache();
//	}

	@Override
	public Map<String,List<String>> getRoleResourceMap() throws TException {
		return roleService.getRoleResourceMap();
	}

	@Override
	public List<RpcRoleResource> listRoleResourceByRoleID(String roleID) throws TException {
		List<RoleResource> listRoleResource = roleService.listRoleResourceByRoleID(roleID);
		return ResultSetUtils.convertToRpc(listRoleResource, roleResourceConverter);
	}

	@Override
	public List<RpcRoleObject> listRoleObjectByRoleID(String roleID, String roleObjectType) throws TException {
		List<RoleObject> listRoleObject = roleService.listRoleObjectByRoleID(roleID, roleObjectType);
		List<RpcRoleObject> rpcRoleObject = ResultSetUtils.convertToRpc(listRoleObject, roleObjectConverter);
		return rpcRoleObject;
	}

}
