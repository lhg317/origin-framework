package com.goldgov.origin.core.web.interceptor.handler.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.goldgov.origin.core.Keys;
import com.goldgov.origin.core.web.annotation.ModuleOperating;
import com.goldgov.origin.core.web.annotation.ModuleResource;
import com.goldgov.origin.core.web.interceptor.handler.IRequestHandler;

/**
 * MVC拦截器处理器，负责记录审计日志
 * 该处理器必须配置在{@link com.goldgov.origin.core.web.interceptor.handler.impl.LocaleChangeHandler LocaleChangeHandler}之后，
 * 如果没有配置或配置在了本处理器之后，则不能使用国际化功能来显示模块名称。
 * @author LiuHG
 * @version 1.0
 */
public class AuditRecordHandler implements IRequestHandler{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
//		System.out.println("请求前："+request.getRequestURI());
		return true;
	}

	@Override
	public boolean postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		if(request.getAttribute(Keys.VALIDATION_ERROR) != null){
			return true;
		}
//		System.out.println("请求后："+request.getRequestURI()+" -> "+modelAndView.getModelMap());
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = ((HandlerMethod)handler);
			Class<? extends Object> beanClass = handlerMethod.getBean().getClass();
			ModuleResource moduleResource = beanClass.getAnnotation(ModuleResource.class);
//			handlerMethod.getMethod();
			ModuleOperating moduleOperating = handlerMethod.getMethodAnnotation(ModuleOperating.class);
			if(moduleResource != null && moduleOperating != null){
//				System.out.println(moduleResource.name()+"->"+moduleOperating.name());
//				MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
//				for (int i = 0; i < methodParameters.length; i++) {
//					System.out.println(methodParameters[i]);
//				}
				String resourceName = moduleResource.name();
				if("".equals(resourceName)){
					resourceName = beanClass.getSimpleName();
				}
				
				String operatingName = moduleOperating.name();
				if("".equals(operatingName)){
					operatingName = handlerMethod.getMethod().getName();
				}
				
//				System.out.println(MessagesHolder.getMessageByPrefix(resourceName)+"->"+MessagesHolder.getMessageByPrefix(operatingName)+", user:"+UserHolder.getUser().getName());
				
				
				
			}
		}
		return true;
	}

	@Override
	public void completion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
//	public String getMessage(String str){
//		String[] nameValue = str.split("[:]");
//		if(nameValue.length >= 2 ){
//			if("i18n".equals(nameValue[0])){
//				String codeStr = str.substring(5);
//				String[] codes = codeStr.split("[+]");
//				StringBuilder strBuilder = new StringBuilder();
//				for (String c : codes) {
//					strBuilder.append(MessagesHolder.getMessage(c));
//				}
//				return strBuilder.toString();
//			}if("locale".equals(nameValue[0])){
//				String codeStr = str.substring(7);
//				String[] codes = codeStr.split("[+]");
//				StringBuilder strBuilder = new StringBuilder();
//				for (String c : codes) {
//					strBuilder.append(MessagesHolder.getMessage(c,PuzzleContext.getSystemLocale()));
//				}
//				return strBuilder.toString();
//			}else{
//				Locale locale = StringUtils.parseLocaleString(nameValue[0]);
//				return MessagesHolder.getMessage(nameValue[1], locale);
//			}
//		} else {
//			return str;
//		}
//		
//	}


}
