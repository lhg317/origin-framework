package com.goldgov.origin.core.discovery.http;

import org.apache.http.client.methods.HttpUriRequest;

public interface Request<T extends HttpUriRequest> extends Wrapper<T>{

	public final String DISCOVERY_HEADER_NAME = "DISCOVERY_SERVICE";
	
	public static final int DEFAULT_TIMEOUT = 3000;
}
