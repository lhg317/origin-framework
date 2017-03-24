package com.goldgov.origin.security.resource.web;

import java.util.List;

import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldgov.origin.security.resource.Resource;
import com.goldgov.origin.security.resource.ResourceContext;

@Controller
@RequestMapping("resources")
public class ResourcesController {
	
	private final String PAGES_BASE_PATH =  this.getClass().getPackage().getName().replace(".", "/")+"/pages/";

	@RequestMapping("/list")
	public String getResources(Model model) throws TException{
		List<Resource> allResources = ResourceContext.getAllResources();

		model.addAttribute("allResources", allResources);
		return PAGES_BASE_PATH + "tree";

	}
}
