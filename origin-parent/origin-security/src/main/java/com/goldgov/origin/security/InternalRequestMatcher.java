package com.goldgov.origin.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.goldgov.origin.core.discovery.http.Request;

public class InternalRequestMatcher implements RequestMatcher{

	private final Log logger = LogFactory.getLog(getClass());
	
	@Override
	public boolean matches(HttpServletRequest request) {
		String header = request.getHeader(Request.DISCOVERY_HEADER_NAME);
		logger.info("############ " + header + " == " + request.getRequestURL());
//		try {
//			URL url = new URL(header);
//			System.out.println("############ " + header + " == " + request.getRequestURL());
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return header != null;
	}

}
