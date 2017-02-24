package com.goldgov.origin.core.web.interceptor.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface IRequestHandler {

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)throws Exception;
	
	public boolean postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView)throws Exception;
	
}
