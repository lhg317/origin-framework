package com.goldgov.origin.core.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.goldgov.origin.core.web.interceptor.handler.IRequestHandler;

/**
 * 
 * @author LiuHG
 * @version 1.0
 */
public class WebInterceptor extends HandlerInterceptorAdapter{

	private Log logger = LogFactory.getLog(this.getClass());
	
	private List<IRequestHandler> requestHandlers;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(requestHandlers != null){
			for (int i = 0; i < requestHandlers.size(); i++) {
				IRequestHandler requestHandler = requestHandlers.get(i);
				boolean result = requestHandler.preHandle(request, response, handler);
				if(logger.isDebugEnabled()){
					logger.debug("[PRE]拦截器"+requestHandler.getClass()+"执行：" + result);
				}
				if(!result){
					//TODO WARN or 抛异常
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(requestHandlers != null){
			for (int i = 0; i < requestHandlers.size(); i++) {
				IRequestHandler requestHandler = requestHandlers.get(i);
				boolean result = requestHandler.postHandle(request, response, handler, modelAndView);
				if(logger.isDebugEnabled()){
					logger.debug("[POST]拦截器"+requestHandler.getClass()+"执行：" + result);
				}
				if(!result){
					//TODO	WARN
					break;
				}
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public List<IRequestHandler> getRequestHandlers() {
		return requestHandlers;
	}

	public void setRequestHandlers(List<IRequestHandler> requestHandlers) {
		this.requestHandlers = requestHandlers;
	}
}
