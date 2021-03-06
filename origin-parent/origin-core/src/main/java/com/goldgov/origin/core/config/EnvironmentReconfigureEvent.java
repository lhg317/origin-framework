package com.goldgov.origin.core.config;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

import com.goldgov.origin.core.discovery.ServiceServer;
import com.goldgov.origin.core.discovery.ServiceType;
import com.goldgov.origin.core.discovery.http.HttpRequestClient;
import com.goldgov.origin.core.discovery.http.Response;
import com.goldgov.origin.core.discovery.http.request.GetRequest;

/**
 * 从配置中心统一获取配置项（如果配置中心有注册的配置服务），注册文件的获取，依据application.properties文件中的application.name
 * 决定，即相同application.name被认为是同一服务，在配置中心根据此名称获取配置文件并更新。此功能主要避免相同服务的配置在发生改动时
 * 需要频繁的修改所有相关节点， 但也不是所有的配置都可以通过本类统一调整，本类是依赖Spring环境的，因此在Spring容器加载前获取配置项
 * 的情况，可能无法替换，例如日志等相关配置。<p>
 * 一般发现服务不会使用本事件类来更新配置文件，因为配置服务是依赖发现服务。如果发现服务和本事件同时存在，则配置服务不会生效，因为
 * 启动中发现服务还未准备好，因此配置服务无法找到发现服务，所以无法更新自己的配置文件。<p>
 * 如果发现服务中未有注册配置服务，则使用各个服务的本地配置文件。统一配置只在启动时获取一次配置，运行时无法热修改<p>
 * 
 * @author LiuHG
 * @since 1.0
 */
public class EnvironmentReconfigureEvent implements ApplicationListener<ApplicationEnvironmentPreparedEvent>{

	private final Log logger = LogFactory.getLog(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
		ConfigurableEnvironment environment = event.getEnvironment();
	
		Properties defaultProperties = new Properties();
		try {
			defaultProperties.load(this.getClass().getResourceAsStream("/default.properties"));
		} catch (IOException e1) {
			throw new RuntimeException("默认配置未找到：/default.properties",e1);
		}
		PropertySource<?> defaultPropertySource = new PropertiesPropertySource("defaultPropertySource", defaultProperties);
		environment.getPropertySources().addAfter("applicationConfigurationProperties",defaultPropertySource);
		
		String discoveryServer = environment.getProperty("discovery.client.discovery-server");
		if(discoveryServer == null){
			logger.info("没有配置注册中心地址，跳过检测配置 ");
			return;
		}
		GetRequest request = new GetRequest(discoveryServer + "?serviceType=" + ServiceType.ConfigurationService);
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
		
		if(serviceServers == null || serviceServers.length == 0){
			return;
		}
		//从远程配置中心获取配置更新当前应用中的配置。
		String applicationName = environment.getProperty("application.name");
//		String securityCode = environment.getProperty("discovery.config.security-code");
		request = new GetRequest(serviceServers[0].getConfigPath() + "?applicationName=" + applicationName);
		httpClient = new HttpRequestClient();
		
		Map<String,Object> configValueMap = null;
		try {
			Response sendRequest = httpClient.sendRequest(request);
			if(sendRequest.isSuccess()){
				configValueMap = sendRequest.toObject(Map.class);
			}else{
				throw new RuntimeException("从配置中心获取配置信息出错: " + serviceServers[0].getConfigPath() + ",status code : " + sendRequest.getStatusCode() + ",return content : " + sendRequest.toString());
			}
		} catch (Exception e) {
			throw new RuntimeException("从配置中心获取配置信息出错: " + serviceServers[0].getConfigPath(),e);
		}finally{
			httpClient.close();
		}
		PropertySource<?> propertySource = new MapPropertySource("configServiceServer", configValueMap);
		environment.getPropertySources().addFirst(propertySource);//.getPropertySources().replace("datasource.c3p0.url", mapPropertySource);
		environment.getPropertySources().get("applicationConfigurationProperties");
		logger.info("从配置中心更新配置成功: " + serviceServers[0].getConfigPath());
	}

}
