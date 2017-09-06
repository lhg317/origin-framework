package com.goldgov.helloworld.custom.role.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.goldgov.origin.modules.role.api.GetAllResourceHandler;
import com.goldgov.origin.modules.role.api.RpcRoleResource;
import com.goldgov.origin.modules.role.api.RpcRoleService;
import com.goldgov.origin.security.resource.Resource;
import com.goldgov.origin.security.resource.ResourceContext;

@Component
public class GetAllResourceHandlerImpl implements GetAllResourceHandler{

	@Autowired
	@Qualifier("rpcRoleService.Client")
	private RpcRoleService.Iface roleService;
	
	@Override
	public String doHandle(HttpServletRequest request, Model model) throws Exception {
		List<Resource> allResources = ResourceContext.getAllResources();
		
		String roleID = request.getParameter("roleID");
		
		List<RpcRoleResource> listRoleResource = roleService.listRoleResourceByRoleID(roleID);
		Map<String,String> resourceOperateMap = new HashMap<String,String>();
		for (RpcRoleResource rpcRoleResource : listRoleResource) {
			String resourceOperate = rpcRoleResource.getResourceOperate();
			resourceOperateMap.put(resourceOperate, resourceOperate);
		}
		model.addAttribute("roleResources", resourceOperateMap);
		model.addAttribute("allResources", allResources);
		return "com/goldgov/origin/modules/role/web/pages/tree";
	}

}
