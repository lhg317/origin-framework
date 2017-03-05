package com.goldgov.origin.modules.role.web;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldgov.origin.core.web.annotation.ModuleOperating;
import com.goldgov.origin.core.web.annotation.ModuleResource;
import com.goldgov.origin.core.web.annotation.OperateType;
import com.goldgov.origin.core.web.token.WebToken;
import com.goldgov.origin.core.web.token.WebToken.TokenHandleType;
import com.goldgov.origin.modules.role.api.RpcRole;
import com.goldgov.origin.modules.role.api.RpcRoleService;

@Controller
@RequestMapping("role")
@ModuleResource(name="Role Manage")
public class RpcRoleController {
	
	private final String PAGES_BASE_PATH =  this.getClass().getPackage().getName().replace(".", "/")+"/pages/";

	@Autowired
	@Qualifier("rpcRoleService.Client")
	private RpcRoleService.Iface userService;
	
	@RequestMapping("/preAdd")
	@WebToken(handle=TokenHandleType.GENERATE)
	public String preAdd() throws TException{
		return PAGES_BASE_PATH + "form";
	}
	
	@RequestMapping("/addUser")
	@WebToken(handle=TokenHandleType.VERIFY)
	@ModuleOperating(name="Add Role",type=OperateType.Save)
	public String addRole(RpcRole role) throws TException{
		userService.addRole(role);
		return "forward:/user/findUserList";
	}
}
