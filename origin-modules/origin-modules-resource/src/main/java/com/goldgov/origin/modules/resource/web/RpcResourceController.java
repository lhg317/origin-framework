package com.goldgov.origin.modules.resource.web;

import java.util.List;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldgov.origin.core.web.annotation.ModuleOperating;
import com.goldgov.origin.core.web.annotation.ModuleResource;
import com.goldgov.origin.modules.resource.api.RpcResource;
import com.goldgov.origin.modules.resource.api.RpcResourceService;

@Controller
@RequestMapping("resource")
@ModuleResource(name="Resource Manage")
public class RpcResourceController {
	
	private final String PAGES_BASE_PATH =  this.getClass().getPackage().getName().replace(".", "/")+"/pages/";

	@Autowired
	@Qualifier("rpcResourceService.Client")
	private RpcResourceService.Iface roleService;
	
	
	@RequestMapping("/findResourceList")
	@ModuleOperating(name="Find All Role")
	public String findRoleList(Model model) throws TException{
		List<RpcResource> allResourceList = roleService.getAllResource();
		model.addAttribute("allResources", allResourceList);
		return PAGES_BASE_PATH + "tree";
	}
}
