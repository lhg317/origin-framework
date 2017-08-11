package com.goldgov.origin.modules.organization.web;

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
	
	@RequestMapping("/preAddOrganization")
	@WebToken(handle=TokenHandleType.GENERATE)
	public String preAddOrganization() throws Exception{
		return PAGE_BASE_PATH + "organizationForm";
	}
	
	@RequestMapping("/addOrganization")
	@WebToken(handle=TokenHandleType.VERIFY)
	@ModuleOperating(name="添加",type=OperateType.ADD)
	public String addOrganization(RpcOrganization organization) throws Exception{
		rpcOrganizationService.addOrganization(organization);
		return "forward:/organization/listOrganization";
	}
	
	@RequestMapping("/deleteOrganization")
	@ModuleOperating(name="删除",type=OperateType.DELETE)
	public String deleteOrganization(@RequestParam("ids") String[] ids) throws Exception{
		rpcOrganizationService.deleteOrganization(Utils.arrayToList(ids));
		return "forward:/organization/listOrganization";
	}
	
	@RequestMapping("/getOrganization")
	@WebToken(handle=TokenHandleType.GENERATE)
	@ModuleOperating(name="查看",type=OperateType.FIND)
	public String getOrganization(@RequestParam("id") String id) throws Exception{
		rpcOrganizationService.getOrganization(id);
		return PAGE_BASE_PATH + "organizationForm";
	}
	
	@RequestMapping("/updateOrganization")
	@ModuleOperating(name="更新",type=OperateType.UPDATE)
	public String updateOrganization(RpcOrganization organization) throws Exception{
		rpcOrganizationService.updateOrganization(organization);
		return "forward:/organization/listOrganization";
	}
	
	@RequestMapping("/listOrganization")
	@ModuleOperating(name="查询",type=OperateType.FIND_LIST)
	public String listOrganization(Model model,RpcOrganizationQuery query) throws Exception{
		query = rpcOrganizationService.listOrganization(query);
		model.addAttribute("query", query);
		return PAGE_BASE_PATH + "organizationList";
	}
	
}
