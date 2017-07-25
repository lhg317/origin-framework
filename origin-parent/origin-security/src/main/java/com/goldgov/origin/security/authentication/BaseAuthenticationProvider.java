package com.goldgov.origin.security.authentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.goldgov.origin.security.UserToken;

public abstract class BaseAuthenticationProvider implements OriginAuthenticationProvider{

	@Override
	public final Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String loginName = (String) authentication.getPrincipal();
        String password = (String)authentication.getCredentials();
        
        boolean result = false;
        try {
        	result = authenticate(loginName,password);
        } catch (Exception e) {
			throw new RuntimeException("认证信息时出现错误：" + loginName,e);
        }
        if(!result){
			throw new BadCredentialsException("用户凭证无效：" + loginName);
		}
        
        UserToken userToken = getUserToken(loginName, password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userToken, password, userToken.getAuthorities());
		return authenticationToken;
	}
	
	protected abstract boolean authenticate(String loginName,String password) throws Exception;
	
	protected abstract UserToken getUserToken(String loginName, String password);

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
