package com.goldgov.origin.config;

import java.util.Map;

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

/**
 * 从配置中心统一获取配置项（如果配置中心有注册的配置服务），此功能主要避免相同服务的配置在发生改动时需要频繁的修改所有相关节点，
 * 但也不是所有的配置都可以通过本类统一调整，本类是依赖Spring环境的，因此在Spring容器加载前获取的配置项的位置，可能无法替换。
 * @author LiuHG
 *
 */
public class EnvironmentReconfigureEvent implements ApplicationListener<ApplicationEnvironmentPreparedEvent>{

	private final Log logger = LogFactory.getLog(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
		ConfigurableEnvironment environment = event.getEnvironment();
		String discoveryServer = environment.getProperty("discovery.client.discovery-server");
		GetRequest request = new GetRequest(discoveryServer + "?serviceType=" + ServiceType.ConfigurationService);//ConfigurationService);
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
		if(serviceServers == null || serviceServers.length == 0){
			return;
		}
		request = new GetRequest(serviceServers[0].getConfigPath());
		httpClient = new HttpRequestClient();
		
		Map<String,Object> configValueMap = null;
		try {
			Response sendRequest = httpClient.sendRequest(request);
			if(sendRequest.isSuccess()){
				configValueMap = sendRequest.toObject(Map.class);
			}else{
				logger.error("从配置中心获取配置信息出错: " + serviceServers[0].getConfigPath() + ",status code : " + sendRequest.getStatusCode() + ",return content : " + sendRequest.toString());
			}
		} catch (Exception e) {
			logger.error("从配置中心获取配置信息出错: " + serviceServers[0].getConfigPath(),e);
		}finally{
			httpClient.close();
		}
		PropertySource<?> propertySource = new MapPropertySource("configServiceServer", configValueMap);
		environment.getPropertySources().addFirst(propertySource);//.getPropertySources().replace("datasource.c3p0.url", mapPropertySource);
		environment.getPropertySources().get("applicationConfigurationProperties");
	}
	
//	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
////		GetRequest request = new GetRequest("http://127.0.0.1:80/server/discovery?serviceType=" + ServiceType.ConfigurationService);//ConfigurationService);
////		HttpRequestClient httpClient = new HttpRequestClient();
////		ServiceServer[] serviceServers = null;
////		try {
////			Response sendRequest = httpClient.sendRequest(request);
////			serviceServers = sendRequest.toObject(ServiceServer[].class);
////		} catch (Exception e) {
////			e.printStackTrace();
////		}finally{
////			httpClient.close();
////		}
////		System.out.println(serviceServers);
//	}

}
