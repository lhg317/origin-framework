package com.goldgov.origin.client.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.goldgov.origin.client.service.DiscoveryClientService;
import com.goldgov.origin.core.discovery.http.HttpRequestClient;
import com.goldgov.origin.core.discovery.http.Response;
import com.goldgov.origin.core.discovery.http.request.GetRequest;
import com.goldgov.origin.core.discovery.rpc.ServiceProviderCenter;

@Service
public class DiscoveryClientServiceImpl implements DiscoveryClientService{

	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private ServiceProviderCenter serviceProviderCenter;
	
	@Value("${discovery.client.discovery-server}")
	private String discoveryServer;
	
	@Override
	public void clearServerCache() {
		serviceProviderCenter.clearServerCache();
	}
	
	@SuppressWarnings("rawtypes")
	public boolean hasService(String serviceName){
		HttpRequestClient requestClient = new HttpRequestClient();
		GetRequest request = new GetRequest(discoveryServer + "?serviceName=" +serviceName);
		try {
			Response response = requestClient.sendRequest(request);
			if(response.getStatusCode() != 200 || !response.toString().equals("SUCCESS")){
				List result = response.toObject(List.class);
				return result.size() > 0;
			}
		} catch (Exception e) {
			logger.warn("获取服务时失败：serviceName=" + serviceName + "，请确定服务发现中心是否启动正常",e);
		}finally{
			requestClient.close();
		}
		return false;
	}
}
