package com.goldgov.origin.config.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldgov.origin.config.service.ConfigService;

@Controller
//@RequestMapping("config")
public class ConfigController {
	
	@Autowired
	private ConfigService configService;
	
//	@Value("${discovery.config.security-code}")
//	private String securityCode;

	@RequestMapping("config")
	public @ResponseBody Map<Object, Object> getConfig(@RequestParam("applicationName") String applicationName){
		Map<Object, Object> config = configService.getConfig(applicationName);
		return config;
	}
}
