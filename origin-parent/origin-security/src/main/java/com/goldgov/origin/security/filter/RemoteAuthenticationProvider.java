package com.goldgov.origin.security.filter;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.goldgov.origin.security.authentication.RemoteAuthenticationToken;

public class RemoteAuthenticationProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!supports(authentication.getClass())) {
			return null;
		}

		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return RemoteAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
