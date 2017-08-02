package com.goldgov.helloworld.custom.role.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.goldgov.origin.modules.role.api.GetAllResourceHandler;
import com.goldgov.origin.security.resource.Resource;
import com.goldgov.origin.security.resource.ResourceContext;

@Component
public class GetAllResourceHandlerImpl implements GetAllResourceHandler{

	@Override
	public String doHandle(HttpServletRequest request, Model model) throws Exception {
		List<Resource> allResources = ResourceContext.getAllResources();
		model.addAttribute("allResources", allResources);
		return "com/goldgov/origin/modules/role/web/pages/tree";
	}

}
