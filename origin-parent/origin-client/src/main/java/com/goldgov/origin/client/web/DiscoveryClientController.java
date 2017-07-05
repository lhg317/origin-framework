package com.goldgov.origin.client.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldgov.origin.client.service.DiscoveryClientService;

@Controller
@RequestMapping("/client")
public class DiscoveryClientController {
	
	@Autowired
	private DiscoveryClientService discoveryClientService;
	
	
	@RequestMapping(path="/update",method=RequestMethod.DELETE)
	public @ResponseBody String clearServerCache(HttpServletRequest request){
		discoveryClientService.clearServerCache();
		return "SUCCESS";
	}
	
	@RequestMapping(path="/update",method=RequestMethod.PUT)
	public @ResponseBody String changeLoadBalancerStrategy(String serviceName,String strategyClass,HttpServletRequest request){
		discoveryClientService.changeLoadBalancerStrategy(serviceName, strategyClass);
		return "SUCCESS";
	}
	
	@RequestMapping(path="/update",method=RequestMethod.PUT)
	public @ResponseBody String changeLoadBalancerStrategy(@RequestParam("serviceName") String serviceName,@RequestParam("weighted") Double weighted,HttpServletRequest request){
		//IRule增加子接口，带权重的IRule
		return "SUCCESS";
	}
	
}
