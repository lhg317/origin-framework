package com.goldgov.origin.core.web.mvc;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler implements HandlerExceptionResolver{

	private final Log logger = LogFactory.getLog(getClass());
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		logger.error("未捕获异常",ex);
		ModelAndView modelAndView = new ModelAndView("/error");
		modelAndView.addObject("status", response.getStatus());
		modelAndView.addObject("exception", ex.getClass().getName());
		modelAndView.addObject("timestamp", new Date());
		modelAndView.addObject("path", request.getRequestURI());
		modelAndView.addObject("message", ex);
//		modelAndView.addObject("trace", ex.getStackTrace());
		return modelAndView;
	}

}
