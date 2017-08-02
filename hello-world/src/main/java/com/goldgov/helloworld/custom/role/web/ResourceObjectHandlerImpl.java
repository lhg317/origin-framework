package com.goldgov.helloworld.custom.role.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.goldgov.origin.modules.role.api.ResourceObjectHandler;
import com.goldgov.origin.modules.user.api.RpcUserQuery;
import com.goldgov.origin.modules.user.api.RpcUserService;

@Component
public class ResourceObjectHandlerImpl implements ResourceObjectHandler{

	@Autowired
	@Qualifier("rpcUserService.Client")
	private RpcUserService.Iface userService;
	
	@Override
	public String doHandle(String roleID,HttpServletRequest request, Model model) throws Exception {
		RpcUserQuery userQuery = new RpcUserQuery();
		userQuery = userService.listUser(userQuery);
		model.addAttribute("query", userQuery);
		return "com/goldgov/origin/modules/role/web/pages/select";
	}

}
