package com.goldgov.origin.security.authentication;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class RemoteAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = -3170960415455904677L;
	
	private final Object principal;
	
	public RemoteAuthenticationToken(String principal,Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
	}

	@Override
	public Object getCredentials() {
		return "";
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

}
