package com.goldgov.helloworld.custom.role.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.goldgov.origin.core.service.rpc.RpcPagingInfo;
import com.goldgov.origin.modules.role.api.ResourceObjectHandler;
import com.goldgov.origin.modules.role.api.RpcRoleObject;
import com.goldgov.origin.modules.role.api.RpcRoleService;
import com.goldgov.origin.modules.user.api.RpcUserQuery;
import com.goldgov.origin.modules.user.api.RpcUserService;

@Component
public class ResourceObjectHandlerImpl implements ResourceObjectHandler{

	@Autowired
	@Qualifier("rpcUserService.Client")
	private RpcUserService.Iface userService;
	
	@Autowired
	@Qualifier("rpcRoleService.Client")
	private RpcRoleService.Iface roleService;
	
	@Override
	public String doHandle(String roleID,HttpServletRequest request, Model model) throws Exception {
		RpcUserQuery userQuery = new RpcUserQuery();
		RpcPagingInfo rpcPagingInfo = new RpcPagingInfo();
		rpcPagingInfo.setPageSize(-1);
		userQuery.setPagingInfo(rpcPagingInfo);
		userQuery = userService.listUser(userQuery);
		
		List<RpcRoleObject> listRoleByObject = roleService.listRoleObjectByRoleID(roleID,"USER");
		Map<String,String> listRoleObjectMap = new HashMap<String,String>();
		for (RpcRoleObject rpcRoleObject : listRoleByObject) {
			String roleObject = rpcRoleObject.getRoleObject();
			listRoleObjectMap.put(roleObject, roleObject);
		}
		
		model.addAttribute("listRoleObject", listRoleObjectMap);
		model.addAttribute("query", userQuery);
		return "com/goldgov/origin/modules/role/web/pages/select";
	}

}
