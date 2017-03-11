package com.goldgov.origin.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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

	
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String loginName = (String) authentication.getPrincipal();
        String password = (String)authentication.getCredentials();
        
        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        grantedAuths.add(new SimpleGrantedAuthority("IS_AUTHENTICATED_ANONYMOUSLY"));
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        if(loginName.equals("liuhg") && password.equals("111111")){
        	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginName, password, grantedAuths);
        	return authenticationToken;
        }

        throw new BadCredentialsException("认证失败：" + loginName);
        
	}

	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
