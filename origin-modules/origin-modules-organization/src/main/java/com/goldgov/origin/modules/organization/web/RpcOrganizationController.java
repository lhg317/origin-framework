package com.goldgov.origin.modules.organization.web;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goldgov.origin.core.discovery.rpc.ObjectConverter.Utils;
import com.goldgov.origin.core.web.annotation.ModuleOperating;
import com.goldgov.origin.core.web.annotation.ModuleResource;
import com.goldgov.origin.core.web.annotation.OperateType;
import com.goldgov.origin.core.web.token.WebToken;
import com.goldgov.origin.core.web.token.WebToken.TokenHandleType;
import com.goldgov.origin.modules.organization.api.OrgObjectHandler;
import com.goldgov.origin.modules.organization.api.RpcOrganization;
import com.goldgov.origin.modules.organization.api.RpcOrganizationQuery;
import com.goldgov.origin.modules.organization.api.RpcOrganizationService;

/**
 * 组织机构管理
 * @author LiuHG
 *
 */
@Controller
@RequestMapping("/organization")
@ModuleResource(name="组织机构管理")
public class RpcOrganizationController {
	
	private final String PAGE_BASE_PATH =  this.getClass().getPackage().getName().replace(".", "/")+"/pages/";

	@Autowired
	@Qualifier("rpcOrganizationService.Client")
	private RpcOrganizationService.Iface rpcOrganizationService;
	
	@Autowired(required=false)
	private OrgObjectHandler orgObjectHandler;
	
	@RequestMapping("/preAddOrg")
	@WebToken(handle=TokenHandleType.GENERATE)
	public String preAddOrganization() throws Exception{
		return PAGE_BASE_PATH + "form";
	}
	
	@RequestMapping("/addOrg")
	@WebToken(handle=TokenHandleType.VERIFY)
	@ModuleOperating(name="添加",type=OperateType.ADD)
	public String addOrganization(RpcOrganization organization) throws Exception{
		rpcOrganizationService.addOrganization(organization);
		return "forward:/organization/listOrg";
	}
	
	@RequestMapping("/deleteOrg")
	@ModuleOperating(name="删除",type=OperateType.DELETE)
	public String deleteOrganization(@RequestParam("ids") String[] ids) throws Exception{
		rpcOrganizationService.deleteOrganization(Utils.arrayToList(ids));
		return "forward:/organization/listOrg";
	}
	
	@RequestMapping("/getOrg")
	@WebToken(handle=TokenHandleType.GENERATE)
	@ModuleOperating(name="查看",type=OperateType.FIND)
	public String getOrganization(Model model,@RequestParam("orgID") String orgID) throws Exception{
		RpcOrganization organization = rpcOrganizationService.getOrganization(orgID);
		model.addAttribute("org", organization);
		return PAGE_BASE_PATH + "form";
	}
	
	@RequestMapping("/updateOrg")
	@ModuleOperating(name="更新",type=OperateType.UPDATE)
	public String updateOrganization(RpcOrganization organization) throws Exception{
		rpcOrganizationService.updateOrganization(organization);
		return "forward:/organization/listOrg";
	}
	
	@RequestMapping("/listOrg")
	@ModuleOperating(name="查询",type=OperateType.FIND_LIST)
	public String listOrganization(Model model,RpcOrganizationQuery query) throws Exception{
		query = rpcOrganizationService.listOrganization(query);
		model.addAttribute("query", query);
		return PAGE_BASE_PATH + "list";
	}
	
	@RequestMapping("/addOrgUser")
	public String saveOrgUser(@RequestParam("orgID")String orgID, @RequestParam("orgUser")String[] orgUser) throws Exception{
		rpcOrganizationService.addOrgUser(orgID, Arrays.asList(orgUser));
		return "forward:/organization/listOrg";
	}
	
	@RequestMapping("/listObject")
	public String listOrgObject(@RequestParam("orgID") String orgID,HttpServletRequest request,Model model) throws Exception{
		if(orgObjectHandler == null){
			throw new IllegalArgumentException("当前Spring上下文中没有OrgObjectHandler的实例，无法执行该请求");
		}
		return orgObjectHandler.doHandle(orgID,request, model);
	}
}
