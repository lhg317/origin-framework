package com.goldgov.origin.core.discovery.http.request;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

import com.goldgov.origin.core.discovery.http.Request;

public class GetRequest implements Request<HttpGet>{

	private HttpGet httpGet;
	
	private int timeout;
	
	public GetRequest(String url){
		this(url,DEFAULT_TIMEOUT);
	}
	
	public GetRequest(String url,int timeout){
		this.timeout = timeout;
		httpGet = new HttpGet(url);
	}

	@Override
	public HttpGet unwrap() {
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeout)
				.setConnectTimeout(timeout)
				.build();
		httpGet.setHeader(Request.DISCOVERY_HEADER_NAME, httpGet.getURI().toString());
		httpGet.setConfig(requestConfig);
		return httpGet;
	}
}
