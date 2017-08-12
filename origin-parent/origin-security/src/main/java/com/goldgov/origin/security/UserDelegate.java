package com.goldgov.origin.security;

public interface UserDelegate {

	public String getUserID();
	public String getLoginName();
//	public void setLoginName(String loginName);
	public String getUserName();
//	public void setUserName(String userName);
	public String[] getRoles();
//	public void setRoles(String[] roles);
	
}
