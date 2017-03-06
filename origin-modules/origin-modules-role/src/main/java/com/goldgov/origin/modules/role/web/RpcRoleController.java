package com.goldgov.origin.modules.role.web;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goldgov.origin.core.web.annotation.ModuleOperating;
import com.goldgov.origin.core.web.annotation.ModuleResource;
import com.goldgov.origin.core.web.annotation.OperateType;
import com.goldgov.origin.core.web.token.WebToken;
import com.goldgov.origin.core.web.token.WebToken.TokenHandleType;
import com.goldgov.origin.modules.role.api.RpcRole;
import com.goldgov.origin.modules.role.api.RpcRoleQuery;
import com.goldgov.origin.modules.role.api.RpcRoleService;

@Controller
@RequestMapping("role")
@ModuleResource(name="Role Manage")
public class RpcRoleController {
	
	private final String PAGES_BASE_PATH =  this.getClass().getPackage().getName().replace(".", "/")+"/pages/";

	@Autowired
	@Qualifier("rpcRoleService.Client")
	private RpcRoleService.Iface roleService;
	
	@RequestMapping("/preAdd")
	@WebToken(handle=TokenHandleType.GENERATE)
	public String preAdd() throws TException{
		return PAGES_BASE_PATH + "form";
	}
	
	@RequestMapping("/addRole")
	@WebToken(handle=TokenHandleType.VERIFY)
	@ModuleOperating(name="Add Role",type=OperateType.Save)
	public String addRole(RpcRole role) throws TException{
		roleService.addRole(role);
		return "forward:/role/findRoleList";
	}
	
	@RequestMapping("/findRole")
	@WebToken(handle=TokenHandleType.GENERATE)
	@ModuleOperating(name="Find Role",type=OperateType.Save)
	public String findRole(@RequestParam("roleID") Integer roleID,Model model) throws TException{
		RpcRole role = roleService.findRoleByID(roleID);
		model.addAttribute("role", role);
		return PAGES_BASE_PATH + "form";
	}
	
	@RequestMapping("/updateRole")
	@WebToken(handle=TokenHandleType.VERIFY)
	@ModuleOperating(name="Update Role",type=OperateType.Save)
	public String updateRole(RpcRole role) throws TException{
		roleService.updateRole(role);
		return "forward:/role/findRoleList";
	}
	
	@RequestMapping("/findRoleList")
	public String findRoleList(RpcRoleQuery query,Model model) throws TException{
		query = roleService.findRoleList(query);
		model.addAttribute("query", query);
		return PAGES_BASE_PATH + "list";
	}
}
