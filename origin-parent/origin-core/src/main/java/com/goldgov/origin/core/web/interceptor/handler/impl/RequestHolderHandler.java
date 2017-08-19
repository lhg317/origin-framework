package com.goldgov.origin.core.web.interceptor.handler.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.goldgov.origin.core.web.interceptor.handler.IRequestHandler;

/**
 * MVC拦截器处理器，负责将HttpServletRequest放入RequestHolder中，便于在Controller中使用。
 * 当请求处理完成后会将HttpServletRequest对象从RequestHolder移除。
 * @author LiuHG
 * @version 1.0
 */
public class RequestHolderHandler implements IRequestHandler{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		RequestHolder.setRequest(request);
		return true;
	}

	@Override
	public boolean postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		return true;
	}
	

	@Override
	public void completion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		RequestHolder.resetRequest();
	}

	public abstract static class RequestHolder {
		
		private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();
		
		static void setRequest(HttpServletRequest request){
			requestHolder.set(request);
		}
		
		static void resetRequest(){
			requestHolder.remove();
		}
		
		public static HttpServletRequest getRequest(){
			HttpServletRequest httpServletRequest = requestHolder.get();
			if(httpServletRequest == null){
				throw new IllegalStateException("当前线程中没有绑定任何HttpServletRequest对象，请确认是否将RequestHolderHandler配置到了MVC拦截器中，且只有Controller请求才能使用本对象。");
			}
			return httpServletRequest;
		}
	}
}
