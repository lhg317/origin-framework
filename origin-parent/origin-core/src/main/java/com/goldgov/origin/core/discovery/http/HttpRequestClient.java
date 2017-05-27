package com.goldgov.origin.core.discovery.http;

import java.io.Closeable;
import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Http请求客户端，内部采用HttpClient实现。负责发起一个Http的请求，并返回请求结果
 * 线程不安全
 * @author LiuHG
 *
 */
public class HttpRequestClient implements Closeable{
	private CloseableHttpClient httpClient = HttpClients.createDefault();

	public Response sendRequest(Request<? extends HttpUriRequest> request) throws Exception{
		CloseableHttpResponse httpResponse = httpClient.execute(request.unwrap());
		return new Response(httpResponse);
	}

	@Override
	public void close() {
		try {
			httpClient.close();
		} catch (IOException e) {}
	}
	
	
}
