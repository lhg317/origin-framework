package com.goldgov.origin.modules.auth.service;

import java.util.Date;

public class AuthAccount {

	private String accountID;
	
	private String principal;
	private String password;
	private Date expiredDate;
	private boolean locked;
	private Date passwordExpired;
	private boolean enabled;
	
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	public Date getPasswordExpired() {
		return passwordExpired;
	}
	public void setPasswordExpired(Date passwordExpired) {
		this.passwordExpired = passwordExpired;
	}
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
