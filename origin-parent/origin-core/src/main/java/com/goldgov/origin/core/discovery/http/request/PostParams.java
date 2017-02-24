package com.goldgov.origin.core.discovery.http.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.Assert;

import com.goldgov.origin.core.discovery.http.Wrapper;

public class PostParams implements Wrapper<List<BasicNameValuePair>>{

	List<BasicNameValuePair> formParams = new ArrayList<BasicNameValuePair>();
	private Map<String, String> params;

	public PostParams(Map<String,String> params){
		Assert.notNull(params);
		this.params = params;
	}
	
	@Override
	public List<BasicNameValuePair> unwrap() {
		for (String key : params.keySet()) {
			String name = params.get(key);
			formParams.add(new BasicNameValuePair(name,params.get(name)));
		}
		return formParams;
	}
	
	public int size(){
		return params.size();
	}
}
