package com.goldgov.origin.modules.role.web;

import java.util.Arrays;
import java.util.List;

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
import com.goldgov.origin.modules.user.api.RpcUserQuery;
import com.goldgov.origin.modules.user.api.RpcUserService;
import com.goldgov.origin.security.resource.Resource;
import com.goldgov.origin.security.resource.ResourceContext;

@Controller
@RequestMapping("role")
@ModuleResource(name="Role Manage")
public class RpcRoleController {
	
	private final String PAGES_BASE_PATH =  this.getClass().getPackage().getName().replace(".", "/")+"/pages/";

	@Autowired
	@Qualifier("rpcRoleService.Client")
	private RpcRoleService.Iface roleService;
	
	@Autowired
	@Qualifier("rpcUserService.Client")
	private RpcUserService.Iface userService;
	
	@RequestMapping("/preAdd")
	@WebToken(handle=TokenHandleType.GENERATE)
	public String preAdd() throws TException{
		return PAGES_BASE_PATH + "form";
	}
	
	@RequestMapping("/addRole")
	@WebToken(handle=TokenHandleType.VERIFY)
	@ModuleOperating(name="Add Role",type=OperateType.ADD)
	public String addRole(RpcRole role) throws TException{
		roleService.addRole(role);
		return "forward:/role/listRole";
	}
	
	@RequestMapping("/saveRoleResource")
	public String saveRoleResource(@RequestParam("roleID")String roleID, @RequestParam("resourceOperate")String[] resourceOperate) throws TException{
		roleService.saveRoleResource(roleID, Arrays.asList(resourceOperate));
		return "forward:/role/listRole";
	}
	
	@RequestMapping("/saveRoleObject")
	public String saveRoleObject(@RequestParam("roleID")String roleID, @RequestParam("loginName")String[] roleObject) throws TException{
		roleService.saveRoleObject(roleID, Arrays.asList(roleObject));
		return "forward:/role/listRole";
	}
	
	@RequestMapping("/deleteRole")
	@ModuleOperating(name="Delete Role",type=OperateType.DELETE)
	public String deleteRole(@RequestParam("roleID") String[] ids) throws TException{
		roleService.deleteRole(Arrays.asList(ids));
		return "forward:/role/listRole";
	}
	
	@RequestMapping("/getRole")
	@WebToken(handle=TokenHandleType.GENERATE)
	@ModuleOperating(name="Find Role",type=OperateType.FIND)
	public String getRole(@RequestParam("roleID") String roleID,Model model) throws TException{
		RpcRole role = roleService.getRole(roleID);
		model.addAttribute("role", role);
		return PAGES_BASE_PATH + "form";
	}
	
	@RequestMapping("/updateRole")
	@WebToken(handle=TokenHandleType.VERIFY)
	@ModuleOperating(name="Update Role",type=OperateType.UPDATE)
	public String updateRole(RpcRole role) throws TException{
		roleService.updateRole(role);
		return "forward:/role/listRole";
	}
	
	@RequestMapping("/listRole")
	public String listRole(RpcRoleQuery query,Model model) throws TException{
		query = roleService.listRole(query);
		model.addAttribute("query", query);
		return PAGES_BASE_PATH + "list";
	}
	
	@RequestMapping("/listResource")
	public String getResources(Model model) throws TException{
		List<Resource> allResources = ResourceContext.getAllResources();

		model.addAttribute("allResources", allResources);
		return PAGES_BASE_PATH + "tree";

	}
	
	@RequestMapping("/findUserSelectList")
	public String listUserSelectList(RpcUserQuery userQuery,Model model) throws TException{
		userQuery = userService.listUser(userQuery);
		model.addAttribute("query", userQuery);
		return PAGES_BASE_PATH + "select";
	}
	
}
