package com.goldgov.origin.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserToken extends UsernamePasswordAuthenticationToken implements UserDelegate{

	private static final long serialVersionUID = 3512951721391711866L;
	
	private String loginName;
	private String userName;
	
	public UserToken(Object principal, Object credentials) {
		super(principal, credentials);
		loginName = principal.toString();
	}
	
	public UserToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		loginName = principal.toString();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getLoginName() {
		return loginName;
	}

	@Override
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}
