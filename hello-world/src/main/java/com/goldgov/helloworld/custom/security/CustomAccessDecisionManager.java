package com.goldgov.helloworld.custom.security;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.goldgov.origin.modules.role.api.RpcRoleService;
import com.goldgov.origin.security.access.BaseAccessDecisionManager;

@Component
public class CustomAccessDecisionManager extends BaseAccessDecisionManager {

	@Autowired
	@Qualifier("rpcRoleService.Client")
	private RpcRoleService.Iface roleService;
	
	/**
	 * key：角色编码，value:角色资源编码
	 */
	@Override
	protected Map<String, List<String>> roleResourceMap()throws Exception {
		return roleService.getRoleResourceMap();
	}
	
}
