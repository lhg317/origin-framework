package com.goldgov.origin.modules.role.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldgov.origin.modules.role.api.ProxyRole;
import com.goldgov.origin.modules.role.api.ProxyRoleQuery;
import com.goldgov.origin.modules.role.api.RpcRole;
import com.goldgov.origin.modules.role.api.RpcRoleQuery;
import com.goldgov.origin.modules.role.api.RpcRoleService;
import com.goldgov.origin.modules.role.dao.RoleDao;
import com.goldgov.origin.modules.role.service.Role;

@Service("roleService")
public class RpcRoleServiceImpl implements RpcRoleService.Iface{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public void addRole(RpcRole role) {
		roleDao.addRole(new ProxyRole(role));
	}

	@Override
	public void deleteRole(List<Integer> ids) throws TException {
		roleDao.deleteRole(ids.toArray(new Integer[0]));
	}

	@Override
	public void updateRole(RpcRole role) throws TException {
		roleDao.updateRole(new ProxyRole(role));
	}

	@Override
	public RpcRole findRoleByID(int roleID) throws TException {
		Role role = roleDao.findRole(roleID);
		return new ProxyRole(role).toRpcRole();
	}

	@Override
	public RpcRoleQuery findRoleList(RpcRoleQuery query) throws TException {
		List<Role> roleList = roleDao.findRoleListByPage(new ProxyRoleQuery(query));
		List<RpcRole> resultList = new ArrayList<>();
		for (Role role : roleList) {
			resultList.add(new ProxyRole(role).toRpcRole());
		}
		query.setResultList(resultList);
		return query;
	}

}
