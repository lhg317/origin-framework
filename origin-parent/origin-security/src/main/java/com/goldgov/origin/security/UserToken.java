package com.goldgov.origin.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserToken extends UsernamePasswordAuthenticationToken implements UserDelegate{

	private static final long serialVersionUID = 3512951721391711866L;
	
	private String loginName;
	private String userName;
	
	private String[] roles;
	
	public UserToken(Object principal, Object credentials) {
		super(principal, credentials);
		loginName = principal.toString();
		roles = new String[0];
	}
	
	public UserToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		loginName = principal.toString();
		roles = new String[authorities.size()];
		int i = 0;
		for (GrantedAuthority grantedAuthority : authorities) {
			roles[i] = grantedAuthority.getAuthority();
			i++;
		}
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

	@Override
	public String[] getRoles() {
		return roles;
	}

	@Override
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
}
