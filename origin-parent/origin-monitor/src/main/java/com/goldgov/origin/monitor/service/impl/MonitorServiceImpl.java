package com.goldgov.origin.monitor.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.goldgov.origin.core.discovery.ServiceServer;
import com.goldgov.origin.core.discovery.http.HttpRequestClient;
import com.goldgov.origin.core.discovery.http.Response;
import com.goldgov.origin.core.discovery.http.request.GetRequest;
import com.goldgov.origin.monitor.service.MonitorInfo;
import com.goldgov.origin.monitor.service.MonitorService;

@Service
public class MonitorServiceImpl implements MonitorService{

	public final static int DEFAULT_EXPIRES_MILLISECOND = 60000;
	
	@Value("${discovery.client.discovery-server}")
	private String discoveryServer;
	
	@Value("${monitor.user.name:monitor}")
	private String user;
	
	@Value("${monitor.user.password:monitor}")
	private String password;
	
	@Value("${monitor.user.role:ACTUATOR}")
	private String role;
	
	@Override
	public List<MonitorInfo> getAllServerMonitorInfo() {
		
		List<ServiceServer> allMonitorServer = getAllMonitorServer();
		
		long expires = System.currentTimeMillis() + DEFAULT_EXPIRES_MILLISECOND;
		String md5Hex = DigestUtils.md5Hex(user + password + role + expires);
		
		for (ServiceServer serviceServer : allMonitorServer) {
			HttpRequestClient httpRequestClient = new HttpRequestClient();
			GetRequest request = new GetRequest("http://127.0.0.1/metrics?expires=" + expires + "&code=" + md5Hex ,false);
			try {
				Response response = httpRequestClient.sendRequest(request);
				System.out.println(serviceServer.getRpcServerAddress() + ":\r\n" + response.toObject(Map.class));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				httpRequestClient.close();
			}
		}
		
		return null;
	}

	@Override
	public List<ServiceServer> getAllMonitorServer() {
		HttpRequestClient httpRequestClient = new HttpRequestClient();
		GetRequest request = new GetRequest(discoveryServer + "?serviceType=" + ServiceServer.ServiceType.ProducerService);
		try {
			Response response = httpRequestClient.sendRequest(request);
			return Collections.unmodifiableList(Arrays.asList(response.toObject(ServiceServer[].class)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			httpRequestClient.close();
		}
		return Collections.emptyList();
	}
	
}
