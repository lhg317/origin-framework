package com.goldgov.origin.core.web.interceptor.handler.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import com.goldgov.origin.core.Keys;
import com.goldgov.origin.core.utils.SpringBeanUtils;
import com.goldgov.origin.core.web.interceptor.handler.IRequestHandler;
import com.goldgov.origin.core.web.interceptor.handler.impl.RequestHolderHandler.RequestHolder;

/**
 * 
 * @author LiuHG
 * @version 1.0
 */
public class LocaleChangeHandler implements IRequestHandler{

	private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();
//	private static final ThreadLocal<HttpServletResponse> responseHolder = new ThreadLocal<HttpServletResponse>();
	
	public static final String DEFAULT_PARAM_NAME = "locale";

	private String paramName = DEFAULT_PARAM_NAME;


	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamName() {
		return this.paramName;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		
		requestHolder.set(request);
//		responseHolder.set(response);
		
		Locale locale = setLocale(request, response);
		
		WebUtils.setSessionAttribute(request, Keys.DEFAULT_LOCALE, locale);
		return true;
	}

	private Locale setLocale(HttpServletRequest request,HttpServletResponse response) {
		String newLocale = request.getParameter(this.paramName);
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		if (newLocale != null) {
			if (localeResolver == null) {
				throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
			}
			localeResolver.setLocale(request, response, StringUtils.parseLocaleString(newLocale));
		}
		return localeResolver.resolveLocale(request);
	}

	@Override
	public boolean postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		return true;
	}
	
	/**
	 * 
	 * @author LiuHG
	 * @version 1.0
	 */
	public abstract static class MessagesHolder{
		
		public static String getMessage(String code){
			return getMessage(code,new String[0]);
		}
		
		public static String getMessage(String code,String[] args){
			return getMessage(code,args,code);
		}
		
		public static String getMessage(String code,String[] args,String defaultMessage){
			return getMessage(code,args,defaultMessage,false);
		}
		
		public static String getMessage(String code,String[] args,String defaultMessage,boolean htmlEscape){
			HttpServletRequest request = requestHolder.get();
			if(request == null){
				throw new IllegalStateException("无法得到消息对象，请确认是否为一个DispatcherServlet请求？");
			}
			RequestContext requestContext = new RequestContext(request);
			return requestContext.getMessage(code, args, defaultMessage, htmlEscape);
		}
		
		public static String getMessage(String code,Locale locale){
			return getMessage(code,null,locale);
		}
		public static String getMessage(String code,String[] args,Locale locale){
			MessageSource messageSource = (MessageSource)SpringBeanUtils.getBean("messageSource");
			return messageSource.getMessage(code, args, locale);
		}
		
		
		public static String getMessageByPrefix(String str){
			String[] nameValue = str.split("[:]");
			if(nameValue.length >= 2 ){
				if("i18n".equals(nameValue[0])){
					String codeStr = str.substring(5);
					String[] codes = codeStr.split("[+]");
					StringBuilder strBuilder = new StringBuilder();
					for (String c : codes) {
						strBuilder.append(getMessage(c));
					}
					return strBuilder.toString();
				}if("locale".equals(nameValue[0])){
					String codeStr = str.substring(7);
					String[] codes = codeStr.split("[+]");
					StringBuilder strBuilder = new StringBuilder();
					Locale locale = (Locale)WebUtils.getSessionAttribute(RequestHolder.getRequest(), Keys.DEFAULT_LOCALE);
					for (String c : codes) {
						
						strBuilder.append(getMessage(c,locale));
					}
					return strBuilder.toString();
				}else{
					Locale locale = StringUtils.parseLocaleString(nameValue[0]);
					return getMessage(nameValue[1], locale);
				}
			} else {
				return str;
			}
		}
	}

	@Override
	public void completion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}


}
