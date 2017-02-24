package com.goldgov.origin.core.discovery.http.request;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldgov.origin.core.discovery.http.Request;

public class JsonRequest extends PostRequest{

	private static final String APPLICATION_JSON = "application/json"; 
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private Object jsonObject;
	
	public JsonRequest(Object jsonObject,String url){
		this(jsonObject,url,3000);
	}
	
	public JsonRequest(Object jsonObject,String url,int timeout){
		super(url,timeout);
		this.jsonObject = jsonObject;
		httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
	}

	@Override
	public HttpPost unwrap() {
		HttpPost httpPost = super.unwrap();
		StringEntity stringEntity = null;
		try {
			String json = objectMapper.writeValueAsString(jsonObject);
			stringEntity = new StringEntity(json);
			stringEntity.setContentType(APPLICATION_JSON);
			stringEntity.setContentEncoding(CONTENT_ENCODING);
		} catch (Exception e) {
			throw new RuntimeException("对象转换json时发生错误",e);
		}
		httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
		httpPost.setHeader(Request.DISCOVERY_HEADER_NAME, httpPost.getURI().toString());
		httpPost.setEntity(stringEntity);
		return httpPost;
		
	}

}
