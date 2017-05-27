package com.goldgov.origin.core.discovery.http;

import org.apache.http.client.methods.HttpUriRequest;

/**
 * Http请求的包装接口
 * @author LiuHG
 * @version 1.0
 */
public interface Request<T extends HttpUriRequest> extends Wrapper<T>{

	public final String DISCOVERY_HEADER_NAME = "DISCOVERY_SERVICE";
	
	/**
	 * Http请求默认的超时时间
	 */
	public static final int DEFAULT_TIMEOUT = 3000;
}
