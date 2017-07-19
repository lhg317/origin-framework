package com.goldgov.origin.core.discovery.http.request;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;

import com.goldgov.origin.core.discovery.http.Request;

public class DeleteRequest implements Request<HttpDelete>{

	protected HttpDelete httpDelete;
	
	private int timeout;
	
	public DeleteRequest(String url){
		this(url,DEFAULT_TIMEOUT);
	}
	
	public DeleteRequest(String url,boolean isRpcRequest){
		this(url,DEFAULT_TIMEOUT,isRpcRequest);
	}
	
	public DeleteRequest(String url,int timeout){
		this(url,timeout,true);
	}
	
	public DeleteRequest(String url,int timeout,boolean isRpcRequest){
		this.timeout = timeout;
		httpDelete = new HttpDelete(url);
		if(isRpcRequest){
			httpDelete.setHeader(Request.DISCOVERY_HEADER_NAME, httpDelete.getURI().toString());
		}
	}
	
	@Override
	public HttpDelete unwrap() {
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeout)
				.setConnectTimeout(timeout)
				.build();
		httpDelete.setConfig(requestConfig);
		return httpDelete;
	}

}
