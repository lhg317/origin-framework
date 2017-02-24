package com.goldgov.origin.config;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import com.goldgov.origin.core.discovery.ServiceServer;
import com.goldgov.origin.core.discovery.ServiceServer.ServiceType;
import com.goldgov.origin.core.discovery.http.HttpRequestClient;
import com.goldgov.origin.core.discovery.http.Response;
import com.goldgov.origin.core.discovery.http.request.GetRequest;

public class EnvironmentReconfigureEvent implements ApplicationListener<ApplicationEnvironmentPreparedEvent>{

	private final Log logger = LogFactory.getLog(getClass());
	
	@Override
	public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
		ConfigurableEnvironment environment = event.getEnvironment();
		String discoveryServer = environment.getProperty("discovery.client.discovery-server");
		GetRequest request = new GetRequest(discoveryServer + "?serviceType=" + ServiceType.ProducerService);//ConfigurationService);
		HttpRequestClient httpClient = new HttpRequestClient();
		ServiceServer[] serviceServers = null;
		try {
			Response sendRequest = httpClient.sendRequest(request);
			serviceServers = sendRequest.toObject(ServiceServer[].class);
		} catch (Exception e) {
			logger.info("注册中心中没有配置服务器: " + discoveryServer);
			logger.debug(e);
		}finally{
			httpClient.close();
		}
		
		//从远程配置中心获取配置更新当前应用中的配置。
		System.out.println((serviceServers == null ? 0:serviceServers.length));
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
//		hashMap.put("datasource.c3p0.url", "1122334rfv");
//		hashMap.put("server.port", "8080");
//		hashMap.put("rpc.server.port", "6666");
		PropertySource<?> propertySource = new MapPropertySource("configServiceServer", hashMap);
		environment.getPropertySources().addFirst(propertySource);//.getPropertySources().replace("datasource.c3p0.url", mapPropertySource);
		environment.getPropertySources().get("applicationConfigurationProperties");
		System.out.println(environment.getProperty("rpc.server.port"));
	}

}
