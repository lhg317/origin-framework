package com.goldgov.origin.modules.auth.service.impl;

import java.util.Date;

import com.goldgov.origin.modules.auth.api.RpcAuthAccount;
import com.goldgov.origin.modules.auth.service.AuthAccount;

public class ProxyAuthAccount extends AuthAccount{

	private RpcAuthAccount rpcAuthAccount;
	
	public ProxyAuthAccount(){
		rpcAuthAccount = new RpcAuthAccount();
	}
	
	public ProxyAuthAccount(RpcAuthAccount rpcAuthAccount){
		this.rpcAuthAccount = rpcAuthAccount;
	}
	
	public ProxyAuthAccount(AuthAccount _authAccount){
		this();
		if(_authAccount == null){
			rpcAuthAccount = null;
			return;
		}
		setAccountID(_authAccount.getAccountID());
		setPrincipal(_authAccount.getPrincipal());
		setPassword(_authAccount.getPassword());
		setExpiredDate(_authAccount.getExpiredDate());
		setLocked(_authAccount.isLocked());
		setPasswordExpired(_authAccount.getPasswordExpired());
		setEnabled(_authAccount.isEnabled());
		
	}
	
	public String getPrincipal() {
		return rpcAuthAccount.getPrincipal();
	}
	public void setPrincipal(String principal) {
		rpcAuthAccount.setPrincipal(principal);;
	}
	public String getPassword() {
		return rpcAuthAccount.getPassword();
	}
	public void setPassword(String password) {
		rpcAuthAccount.setPassword(password);;
	}
	public Date getExpiredDate() {
		long time = rpcAuthAccount.getExpiredDate();
		if(time > 0){
			return new Date(time);
		}
		return null;
	}
	public void setExpiredDate(Date expiredDate) {
		if(expiredDate != null){
			rpcAuthAccount.setExpiredDate(expiredDate.getTime());
		}
	}
	public boolean isLocked() {
		return rpcAuthAccount.isLocked();
	}
	public void setLocked(Boolean locked) {
		rpcAuthAccount.setLocked(locked);
	}
	public Date getPasswordExpired() {
		long time = rpcAuthAccount.getPasswordExpired();
		if(time > 0){
			return new Date(time);
		}
		return null;
	}
	public void setPasswordExpired(Date passwordExpired) {
		if(passwordExpired != null){
			rpcAuthAccount.setPasswordExpired(passwordExpired.getTime());
		}
	}
	public boolean isEnabled() {
		return rpcAuthAccount.isEnabled();
	}
	public void setEnabled(Boolean enabled) {
		rpcAuthAccount.setEnabled(enabled);
	}
	public String getAccountID() {
		return rpcAuthAccount.getAccountID();
	}
	public void setAccountID(String accountID) {
		rpcAuthAccount.setAccountID(accountID);
	}

	public RpcAuthAccount toRpcObject() {
		return rpcAuthAccount;
	}
}
