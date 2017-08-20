package com.goldgov.helloworld.custom.role.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.goldgov.origin.modules.role.api.ObjectRoleList;
import com.goldgov.origin.modules.role.api.RpcRole;
import com.goldgov.origin.modules.role.api.RpcRoleService;

@Component
public class UserRoleList implements ObjectRoleList{

	@Autowired
	@Qualifier("rpcRoleService.Client")
	private RpcRoleService.Iface roleService;
	
	@Override
	public List<RpcRole> listRole(String loginName) throws Exception{
		return roleService.listRoleByObject(loginName,"USER");
	}

}
