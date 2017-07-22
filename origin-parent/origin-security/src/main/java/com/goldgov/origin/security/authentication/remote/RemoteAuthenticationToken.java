package com.goldgov.origin.security.authentication.remote;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class RemoteAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = -3170960415455904677L;
	
	private Object principal;
	
	private long expires;
	
	public RemoteAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
	}
	
//	public RemoteAuthenticationToken(String principal,long expires,Collection<? extends GrantedAuthority> authorities) {
//		super(authorities);
//		this.principal = principal;
//		this.expires = expires;
//	}

	@Override
	public Object getCredentials() {
		return "";
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

	public long getExpires() {
		return expires;
	}

	public void setPrincipal(Object principal) {
		this.principal = principal;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}
	
}
