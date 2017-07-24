package com.goldgov.origin.modules.auth.service;

import java.util.Date;

public interface AuthAccountService {

	public void addAuthAccount(AuthAccount authAccount);
	
	public AuthAccount getAuthAccount(String principal, String password);
	
	public void updatePassword(String principal,String oldPass,String newPass);
	
	public void updateExpiredDate(String principal,Date expiredDate);
	
	public void setEnabled(String principal,boolean enabled);
	
	public void setLocked(String principal,boolean locked);
	
	public void deleteAuthAccount(String principal);
	
	public void deleteAuthAccount(String[] principal);
	
	public String getPassword(String principal);
	
}
