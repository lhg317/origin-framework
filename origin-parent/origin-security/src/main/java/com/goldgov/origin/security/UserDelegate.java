package com.goldgov.origin.security;

import java.util.Map;

public interface UserDelegate {

	String getUserID();
	String getLoginName();
	String getUserName();
	String[] getRoles();
	Map<String,Object> getAttributes();
	void setAttribute(String name,Object value);
	Object getAttribute(String name);
	
}
