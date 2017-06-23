package com.goldgov.origin.server.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldgov.origin.core.discovery.ServiceServer;
import com.goldgov.origin.core.discovery.ServiceServer.ServiceType;
import com.goldgov.origin.core.discovery.rpc.RpcServiceInstance;
import com.goldgov.origin.server.service.DiscoveryServerService;
import com.goldgov.origin.server.service.ServiceHealthChecker;


/**
 * 服务发现中心MVC控制器，负责接收服务端的注册服务请求，同时也是获取服务的唯一通道，因为模块微服务化，注册端不应该
 * 直接使用服务发现中心的任何业务接口。
 * @author LiuHG
 * @since 1.0
 */
@Controller
@RequestMapping("/server")
public class DiscoveryServerController {

	private final String PAGES_BASE_PATH =  this.getClass().getPackage().getName().replace(".", "/");
	
	@Autowired
	private DiscoveryServerService discoveryService;
	
	@Autowired
	private ServiceHealthChecker healthChecker;

	private PropertyPlaceholderHelper placeholderHelper = new PropertyPlaceholderHelper("{","}");
	
	
	@RequestMapping(path="/discovery",method=RequestMethod.POST)
	public @ResponseBody String serviceDiscovery(@RequestBody ServiceServer serviceObject,HttpServletRequest request){
		String remoteHost = request.getRemoteHost();
		
		//FIX IP或域名？
		serviceObject.setServerIP(request.getRemoteAddr());
		
		Properties properties = new Properties();
		properties.put("serverIP", remoteHost);
		serviceObject.setHealthPath(processPlaceholder(serviceObject.getHealthPath(),properties));
		serviceObject.setUpdatePath(processPlaceholder(serviceObject.getUpdatePath(),properties));
		serviceObject.setConfigPath(processPlaceholder(serviceObject.getConfigPath(),properties));
		serviceObject.setRegisterDate(new Date());
		
		discoveryService.addRequiredServiceName(serviceObject.getRpcServerAddress(), serviceObject.getRequiredServerNames());
		discoveryService.addOptionalServiceName(serviceObject.getRpcServerAddress(), serviceObject.getOptionalServerNames());
		
		discoveryService.addService(serviceObject);
		return "SUCCESS";
	}
	
	@RequestMapping(path="/discovery",method=RequestMethod.GET,params="serviceName")
	public @ResponseBody List<ServiceServer> findServices(@RequestParam(name="serviceName") String serviceName,HttpServletRequest request){
		List<RpcServiceInstance> services = discoveryService.getServices(serviceName);
		List<ServiceServer> serverList = new ArrayList<>(services.size());
		for (RpcServiceInstance rpcServiceInstance : services) {
			serverList.add(rpcServiceInstance.getServiceServer());
		}
		return serverList;
	}
	
	@RequestMapping(path="/discovery",method=RequestMethod.GET,params="serviceType")
	public @ResponseBody List<ServiceServer> findServiceServers(@RequestParam(name="serviceType") String serviceType,HttpServletRequest request){
		List<ServiceServer> serviceServers = discoveryService.getServiceServers(ServiceType.valueOf(serviceType));
		return serviceServers;
	}
	
	
	@RequestMapping("overview")
	public String serviceInfoOverview(Model model){
		
		Map<String, List<String>> allRequiredServics = discoveryService.getAllRequiredServiceName();
		Map<String, List<String>> allOptionalServics = discoveryService.getAllOptionalServiceName();
		Map<String, ServiceServer> clientMapping = discoveryService.getClientMapping();
		
		Set<String> clientAddressSet = allRequiredServics.keySet();
		ClientHealth clientHealth = new ClientHealth();
		for (String clientAddress : clientAddressSet) {
			ServiceServer serviceServer = clientMapping.get(clientAddress);
			List<String> requiredServiceNames = allRequiredServics.get(clientAddress);
			List<String> optionalServiceNames = allOptionalServics.get(clientAddress);
			List<ServiceHealth> healthList = new ArrayList<>();
			for (String serviceName : requiredServiceNames) {
				List<RpcServiceInstance> serviceList = discoveryService.getServices(serviceName);
				healthList.add(new ServiceHealth(serviceName, serviceList.size() > 0 ? HealthState.UP : HealthState.DOWN));
			}
			for (String serviceName : optionalServiceNames) {
				healthList.add(new ServiceHealth(serviceName, HealthState.MAYBE));
			}
			clientHealth.addHealthState(serviceServer, healthList);
		}
		Map<String, List<RpcServiceInstance>> allServices = discoveryService.getAllServices();
		model.addAttribute("clientHealth",clientHealth);
		model.addAttribute("allServices",allServices);
		model.addAttribute("checkInterval", healthChecker.getCheckInterval()/1000);
		model.addAttribute("lastCheckDate", healthChecker.getLastCheckDate());
		model.addAttribute("checkFailTotal", healthChecker.getCheckFailTotal());
		
		return PAGES_BASE_PATH + "/overview";
	}
	
	private String processPlaceholder(String text,Properties properties){
		return placeholderHelper.replacePlaceholders(text, properties);
	}
	
	public static class ClientHealth {

		private Map<String,HealthState> clientHealthMap = new HashMap<>();

		private Map<String,List<ServiceHealth>> serviceHealthMap = new HashMap<>();
		
		private Map<String,ServiceServer> serviceServerMap = new HashMap<>();
		
		public ClientHealth(){}
		
		public void addHealthState(ServiceServer serviceServer,List<ServiceHealth> arg1) {
//			 String clientAddress = serviceServer.getRpcServerAddress();
			 String healthPath = serviceServer.getHealthPath();
			 serviceHealthMap.put(healthPath, arg1);
			 clientHealthMap.put(healthPath, HealthState.UP);
			 serviceServerMap.put(healthPath, serviceServer);
			 if(arg1 == null || arg1.size() == 0){
				 return;
			 }
			 for (ServiceHealth serviceHealth : arg1) {
				if(serviceHealth.getState() == HealthState.DOWN){
					clientHealthMap.put(healthPath, HealthState.DOWN);
					break;
				}
			}
		}

		public Map<String, HealthState> getClientHealthMap() {
			return clientHealthMap;
		}

		public Map<String, List<ServiceHealth>> getServiceHealthMap() {
			return serviceHealthMap;
		}
		
		public Map<String, ServiceServer> getServiceServerMap() {
			return serviceServerMap;
		}
		
		public int getClientNum(){
			return clientHealthMap.size();
		}
		
		public int getServiceNum(String clientAddress){
			List<ServiceHealth> serviceList = serviceHealthMap.get(clientAddress);
			if(serviceList == null){
				return 0;
			}
			return serviceList.size();
		}

	}
	
	public static class ServiceHealth{
		private String serviceName;
		private HealthState state;
		
		public ServiceHealth(){}
		
		public ServiceHealth(String serviceName,HealthState state){
			this.serviceName = serviceName;
			this.state = state;
		}
		
		public String getServiceName() {
			return serviceName;
		}
		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}
		public HealthState getState() {
			return state;
		}
		public void setState(HealthState state) {
			this.state = state;
		}
	}
	
	public enum HealthState{
		UP,DOWN,MAYBE;
	}
	
}
