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
//@RequestMapping("/client")
public class DiscoveryClientController {
	
	@Autowired(required=false)
	private DiscoveryClientService discoveryClientService;
	
	
	@RequestMapping(path="/update",method=RequestMethod.DELETE)
	public @ResponseBody String clearServerCache(HttpServletRequest request){
		getClientService().clearServerCache();
		return "SUCCESS";
	}
	
	@RequestMapping(path="/update",method=RequestMethod.PUT,params={"serviceName","HttpServletRequest","!weighted"})
	public @ResponseBody String changeLoadBalancerStrategy(String serviceName,String strategyClass,HttpServletRequest request){
		getClientService().changeLoadBalancerStrategy(serviceName, strategyClass);
		return "SUCCESS";
	}
	
	@RequestMapping(path="/update",method=RequestMethod.PUT,params={"serviceName","weighted","!strategyClass"})
	public @ResponseBody String changeLoadBalancerStrategy(@RequestParam("serviceName") String serviceName,@RequestParam("weighted") Double weighted,HttpServletRequest request){
		//TODO 
		return "SUCCESS";
	}
	
	private DiscoveryClientService getClientService(){
		if(discoveryClientService == null){
			throw new RuntimeException("您可能尚未开启RPC自动配置@EnableRpcConfiguration");
		}
		return discoveryClientService;
	}
	
}
