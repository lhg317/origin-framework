package com.goldgov.origin.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.goldgov.origin.core.Keys;
import com.goldgov.origin.core.web.interceptor.handler.impl.RequestHolderHandler.RequestHolder;

public abstract class UserHolder {

	public static UserDelegate getUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null){
			return (UserDelegate) authentication.getPrincipal();
		}else{
			HttpServletRequest request = RequestHolder.getRequest();
			return  (UserDelegate)request.getSession().getAttribute(Keys.CURRENT_USER);
		}	
	}
}
