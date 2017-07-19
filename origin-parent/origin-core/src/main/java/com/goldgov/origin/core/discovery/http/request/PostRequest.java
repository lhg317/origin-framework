package com.goldgov.origin.core.discovery.http.request;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import com.goldgov.origin.core.discovery.http.Request;

public class PostRequest implements Request<HttpPost>{

	protected static final String CONTENT_ENCODING = "UTF-8";
	
	protected final HttpPost httpPost;
	
	private int timeout;

	private PostParams params;
	
	public PostRequest(String url){
		this(url,null);
	}
	
	public PostRequest(String url,boolean isRpcRequest){
		this(url,DEFAULT_TIMEOUT,null,isRpcRequest);
	}
	
	public PostRequest(String url,PostParams params){
		this(url,DEFAULT_TIMEOUT,params);
	}
	
	public PostRequest(String url,int timeout){
		this(url,DEFAULT_TIMEOUT,null);
	}
	
	public PostRequest(String url,int timeout,boolean isRpcRequest){
		this(url,DEFAULT_TIMEOUT,null,isRpcRequest);
	}
	
	public PostRequest(String url,int timeout,PostParams params){
		this(url,timeout,params,true);
	}
	
	public PostRequest(String url,int timeout,PostParams params,boolean isRpcRequest){
		this.timeout = timeout;
		this.params = params;
		httpPost = new HttpPost(url);
		if(isRpcRequest){
			httpPost.setHeader(Request.DISCOVERY_HEADER_NAME, httpPost.getURI().toString());
		}
	}
	
	public HttpPost unwrap(){
		
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeout)
				.setConnectTimeout(timeout)
				.build();
		
		httpPost.setConfig(requestConfig);
		
		if(params != null && params.size() > 0){
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(params.unwrap(),CONTENT_ENCODING));
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("不支持的编码格式" + CONTENT_ENCODING,e);
			}
		}
		return httpPost;
	}
}
