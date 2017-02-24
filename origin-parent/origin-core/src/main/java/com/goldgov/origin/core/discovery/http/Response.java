package com.goldgov.origin.core.discovery.http;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Response {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private CloseableHttpResponse httpResponse;

	private String responeStr = null;
	
	Response(CloseableHttpResponse httpResponse){
		this.httpResponse = httpResponse;
	}
	
	public int getStatusCode(){
		StatusLine statusLine = httpResponse.getStatusLine();
		return statusLine.getStatusCode();
	}
	
	public HttpEntity getEntity(){
		return httpResponse.getEntity();
	}
	
	public boolean isSuccess(){
		return getStatusCode() == 200;
	}
	
	public <T> T toObject(Class<T> clazz){
		String json = toString();
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException("json转对象异常：" + clazz + "," + json ,e);
		}
	}
	
	public String toString(){
		if(responeStr == null){
			try {
				responeStr = EntityUtils.toString(httpResponse.getEntity());
			} catch (Exception e) {
				throw new RuntimeException("响应实体解析字符串时发生错误",e);
			}
		}
		return responeStr;
	}

}
