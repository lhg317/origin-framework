package com.goldgov.origin.security;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author LiuHG
 * @version 1.0
 */
public class DefaultAuthenticationProvider implements AuthenticationProvider {

	private final Log logger = LogFactory.getLog(getClass());
	
	private String defaultPassword;
	
	public DefaultAuthenticationProvider(){
		defaultPassword = UUID.randomUUID().toString().replaceAll("-","");
		logger.error("使用默认密码：" + defaultPassword );
	}
	
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String loginName = (String) authentication.getPrincipal();
        String password = (String)authentication.getCredentials();
        
        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        grantedAuths.add(new SimpleGrantedAuthority("IS_AUTHENTICATED_ANONYMOUSLY"));
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        
        if(loginName.equals("user") && password.equals(defaultPassword)){
        	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginName, password, grantedAuths);
        	return authenticationToken;
        }

        throw new BadCredentialsException("认证失败：" + loginName);
        
	}

	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
