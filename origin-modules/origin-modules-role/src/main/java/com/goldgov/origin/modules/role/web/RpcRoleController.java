package com.goldgov.origin.modules.role.web;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

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
import com.goldgov.origin.modules.role.api.GetAllResourceHandler;
import com.goldgov.origin.modules.role.api.ResourceObjectHandler;
import com.goldgov.origin.modules.role.api.RpcRole;
import com.goldgov.origin.modules.role.api.RpcRoleQuery;
import com.goldgov.origin.modules.role.api.RpcRoleService;

@Controller
@RequestMapping("role")
@ModuleResource(name="Role Manage")
public class RpcRoleController {
	
	private final String PAGE_BASE_PATH =  this.getClass().getPackage().getName().replace(".", "/")+"/pages/";

	@Autowired
	@Qualifier("rpcRoleService.Client")
	private RpcRoleService.Iface roleService;
	
//	@Autowired
//	@Qualifier("rpcUserService.Client")
//	private RpcUserService.Iface userService;
	
	@Autowired(required=false)
	private ResourceObjectHandler resourceObjectHandler;
	
	@Autowired(required=false)
	private GetAllResourceHandler getAllResourceHandler;
	
	@RequestMapping("/preAdd")
	@WebToken(handle=TokenHandleType.GENERATE)
	public String preAdd() throws Exception{
		return PAGE_BASE_PATH + "form";
	}
	
	@RequestMapping("/addRole")
	@WebToken(handle=TokenHandleType.VERIFY)
	@ModuleOperating(name="Add Role",type=OperateType.ADD)
	public String addRole(RpcRole role) throws Exception{
		roleService.addRole(role);
		return "forward:/role/listRole";
	}
	
	@RequestMapping("/saveRoleResource")
	public String saveRoleResource(@RequestParam("roleID")String roleID, @RequestParam("resourceOperate")String[] resourceOperate) throws Exception{
		roleService.saveRoleResource(roleID, Arrays.asList(resourceOperate));
		return "forward:/role/listRole";
	}
	
	@RequestMapping("/saveRoleObject")
	public String saveRoleObject(@RequestParam("roleID")String roleID, @RequestParam("roleObject")String[] roleObject) throws Exception{
		roleService.saveRoleObject(roleID, Arrays.asList(roleObject));
		return "forward:/role/listRole";
	}
	
	@RequestMapping("/deleteRole")
	@ModuleOperating(name="Delete Role",type=OperateType.DELETE)
	public String deleteRole(@RequestParam("roleID") String[] ids) throws Exception{
		roleService.deleteRole(Arrays.asList(ids));
		return "forward:/role/listRole";
	}
	
	@RequestMapping("/getRole")
	@WebToken(handle=TokenHandleType.GENERATE)
	@ModuleOperating(name="Find Role",type=OperateType.FIND)
	public String getRole(@RequestParam("roleID") String roleID,Model model) throws Exception{
		RpcRole role = roleService.getRole(roleID);
		model.addAttribute("role", role);
		return PAGE_BASE_PATH + "form";
	}
	
	@RequestMapping("/updateRole")
	@WebToken(handle=TokenHandleType.VERIFY)
	@ModuleOperating(name="Update Role",type=OperateType.UPDATE)
	public String updateRole(RpcRole role) throws Exception{
		roleService.updateRole(role);
		return "forward:/role/listRole";
	}
	
	@RequestMapping("/listRole")
	public String listRole(RpcRoleQuery query,Model model) throws Exception{
		query = roleService.listRole(query);
		model.addAttribute("query", query);
		return PAGE_BASE_PATH + "list";
	}
	
	/**
	 * 获取所有受保护的资源对象，并放置到名称为“allResources”的属性中便于界面直接使用。
	 * allResources为List&lt;{@link com.goldgov.origin.security.resource.Resource Resource}&gt;对象
	 */
	@RequestMapping("/listResource")
	public String getResources(HttpServletRequest request, Model model) throws Exception{
		if(getAllResourceHandler == null){
			throw new IllegalArgumentException("当前Spring上下文中没有GetAllResourceHandler的实例，无法执行该请求");
		}
		return getAllResourceHandler.doHandle(request,model);
	}
	
	@RequestMapping("/listObject")
	public String listObject(@RequestParam("roleID") String roleID,HttpServletRequest request,Model model) throws Exception{
		if(resourceObjectHandler == null){
			throw new IllegalArgumentException("当前Spring上下文中没有ResourceObjectHandler的实例，无法执行该请求");
		}
		return resourceObjectHandler.doHandle(roleID,request, model);
	}
	
//	@RequestMapping("/findUserSelectList")
//	public String listUserSelectList(RpcUserQuery userQuery,Model model) throws Exception{
//		userQuery = userService.listUser(userQuery);
//		model.addAttribute("query", userQuery);
//		return PAGE_BASE_PATH + "select";
//	}
	
}
