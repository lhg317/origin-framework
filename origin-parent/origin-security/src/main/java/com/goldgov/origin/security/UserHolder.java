package com.goldgov.origin.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.goldgov.origin.core.web.interceptor.handler.impl.RequestHolderHandler.RequestHolder;

/**
 * 获取当前用户信息的工具类
 * @author LiuHG
 *
 */
public final class UserHolder {
	
	private UserHolder(){}

	public static UserDelegate getUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null){
			return (UserDelegate) authentication;
		}else{
			HttpServletRequest request = RequestHolder.getRequest();
			return  (UserDelegate)request.getSession().getAttribute(SecurityConstants.CURRENT_USER);
		}	
	}
}
