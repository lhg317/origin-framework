package com.goldgov.origin.monitor.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.goldgov.origin.core.discovery.ServiceServer;
import com.goldgov.origin.core.discovery.ServiceType;
import com.goldgov.origin.core.discovery.http.HttpRequestClient;
import com.goldgov.origin.core.discovery.http.Response;
import com.goldgov.origin.core.discovery.http.request.GetRequest;
import com.goldgov.origin.monitor.service.MetricsPropertyMapper;
import com.goldgov.origin.monitor.service.MonitorInfo;
import com.goldgov.origin.monitor.service.MonitorService;

/**
 * 仅监控网关服务
 * @author LiuHG
 * @version 1.0
 */
@Service
public class MonitorServiceImpl implements MonitorService{

	private final Log logger = LogFactory.getLog(getClass());
	
	public final static int DEFAULT_EXPIRES_MILLISECOND = 60000;
	
	@Value("${discovery.client.discovery-server}")
	private String discoveryServer;
	
	@Value("${monitor.user.name:monitor}")
	private String user;
	
	@Value("${monitor.user.password:monitor}")
	private String password;
	
	@Value("${monitor.user.role:ACTUATOR}")
	private String role;
	
	@Autowired(required=false)
	private MetricsPropertyMapper metricsPropertyMapper;
	
	HttpRequestClient httpRequestClient = new HttpRequestClient();
	
	@Override
	@SuppressWarnings("unchecked")
	public List<MonitorInfo> getAllServerMonitorInfo() {
		
		List<MonitorInfo> result = new ArrayList<>();
		
		List<ServiceServer> allMonitorServer = getAllMonitorServer();
		
		long expires = System.currentTimeMillis() + DEFAULT_EXPIRES_MILLISECOND;
		String md5Hex = DigestUtils.md5Hex(user + password + role + expires);
		
//		System.out.println("#### " + expires + " - " + new Date(expires).toLocaleString());
		
//		HttpRequestClient httpRequestClient = new HttpRequestClient();
//		try{
			for (ServiceServer serviceServer : allMonitorServer) {
				
				GetRequest request = new GetRequest(serviceServer.getMetricsPath() + "?expires=" + expires + "&code=" + md5Hex ,false);
				try {
					Response response = httpRequestClient.sendRequest(request);
					if(response.isSuccess()){
						Map<String,Number> propValueMap = response.toObject(Map.class);
						if(metricsPropertyMapper == null){
							metricsPropertyMapper = new DefaultPropertyMapperImpl();
						}
						MonitorInfo monitorInfo = metricsPropertyMapper.getMonitorInfo(propValueMap);
						monitorInfo.setServer(serviceServer);
						result.add(monitorInfo);
					}else{
						logger.error("获取网关服务监控指标时出现错误：" + serviceServer.getMetricsPath() + "," + response.toString());
					}
				} catch (Exception e) {
					throw new RuntimeException("获取网关服务监控指标时出现错误：" + serviceServer.getMetricsPath(), e);
				}
			}
//		}finally {
//			httpRequestClient.close();
//		}
		
		
		return result;
	}

	@Override
	public List<ServiceServer> getAllMonitorServer() {
		GetRequest request = new GetRequest(discoveryServer + "?serviceType=" + ServiceType.WebGateService);
		try {
			Response response = httpRequestClient.sendRequest(request);
			if(response.isSuccess()){
				return Collections.unmodifiableList(Arrays.asList(response.toObject(ServiceServer[].class)));
			}else{
				logger.error("获取所有网关服务时出现错误：" + response.toString());
			}
		} catch (Exception e) {
			throw new RuntimeException("获取所有网关服务时出现错误" , e);
		}
//		finally {
//			httpRequestClient.close();
//		}
		return Collections.emptyList();
	}
	
}
