package com.goldgov.origin.core.web.interceptor.handler.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.goldgov.origin.core.Keys;
import com.goldgov.origin.core.web.interceptor.handler.IRequestHandler;

/**
 * Freemarker拦截器事件处理对象，该处理对象最好放在内置处理对象的最后一个。
 * @author LiuHG
 * @version 1.0
 */
public class FreemarkModelHandler implements IRequestHandler{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = ((HandlerMethod)handler);
			//判断被请求的方法参数是否有被标注@Valid注解的（被标注@Valid的说明需要进行字段校验）
			RequestMapping requestMapping = handlerMethod.getBeanType().getAnnotation(RequestMapping.class);
			if(requestMapping != null){
				request.setAttribute(Keys.ORIGIN_MODEL_PATH, requestMapping.value());
			}
		}
		return true;
	}

	@Override
	public boolean postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
//		if(handler instanceof HandlerMethod){
//			HandlerMethod handlerMethod = ((HandlerMethod)handler);
//			//判断被请求的方法参数是否有被标注@Valid注解的（被标注@Valid的说明需要进行字段校验）
//			RequestMapping requestMapping = handlerMethod.getBeanType().getAnnotation(RequestMapping.class);
//			if(requestMapping != null){
//				request.setAttribute(Keys.PUZZLE_MODEL_PATH, requestMapping.value());
//			}
//		}
		request.getAttribute(Keys.ORIGIN_EXCEPTION);
		return true;
	}



}
