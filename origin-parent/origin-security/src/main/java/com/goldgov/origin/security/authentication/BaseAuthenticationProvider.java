package com.goldgov.origin.security.authentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.goldgov.origin.security.UserToken;

/**
 * 如不满足，自行实现OriginAuthenticationProvider接口
 * @author LiuHG
 * @version 1.0
 */
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
	
	/**
	 * 用户凭证认证
	 * @param loginName
	 * @param password
	 * @return true 认证成功，false，认证失败
	 * @throws Exception
	 */
	protected abstract boolean authenticate(String loginName,String password) throws Exception;
	
	/**
	 * 当用户认证成功后，会调用此方法构造用户UserToken对象
	 * @param loginName
	 * @param password
	 * @return
	 */
	protected abstract UserToken getUserToken(String loginName, String password);

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
