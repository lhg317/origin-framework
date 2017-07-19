package com.goldgov.origin.security.filter;

import java.util.Date;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.goldgov.origin.security.authentication.RemoteAuthenticationToken;

public class RemoteAuthenticationProvider implements AuthenticationProvider{

	private ResourceBundle bundle = ResourceBundle.getBundle("application");
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!supports(authentication.getClass())) {
			return null;
		}
		
		String user = getConfigValue("monitor.user.name","monitor");
		String password = getConfigValue("monitor.user.password",null);
		String role = getConfigValue("monitor.user.role","ACTUATOR");
		
		if(password == null){
			return null;
		}
		
		RemoteAuthenticationToken token = (RemoteAuthenticationToken)authentication;
		Object principal = token.getPrincipal();
		Date expiresDate = new Date(token.getExpires());
		String md5Hex = DigestUtils.md5Hex(user + password + role + token.getExpires());
		if(md5Hex.equals(principal.toString())){
			if(expiresDate.before(new Date())){
				throw new BadCredentialsException("监控登录被拒绝，您提供的凭证已过期");
			}
			return authentication;
		}
		throw new BadCredentialsException("监控登录被拒绝，您提供的凭证有误");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return RemoteAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
	private String getConfigValue(String key,String defaultValue){
		try{
			return bundle.getString(key);
		}catch(MissingResourceException e){
			return defaultValue;
		}
	}

}
