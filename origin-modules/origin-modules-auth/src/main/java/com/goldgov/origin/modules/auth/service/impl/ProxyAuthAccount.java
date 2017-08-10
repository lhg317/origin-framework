package com.goldgov.origin.modules.auth.service.impl;

import java.util.Date;

import com.goldgov.origin.core.discovery.rpc.ProxyObject;
import com.goldgov.origin.modules.auth.api.RpcAuthAccount;
import com.goldgov.origin.modules.auth.service.AuthAccount;

public class ProxyAuthAccount extends AuthAccount implements ProxyObject<RpcAuthAccount>{

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
		if(rpcAuthAccount.isSetPrincipal()){
			return rpcAuthAccount.getPrincipal();
		}else{
			return null;
		}
		
	}
	public void setPrincipal(String principal) {
		if(principal == null){
			rpcAuthAccount.setPrincipal(principal);
		}
	}
	public String getPassword() {
		if(rpcAuthAccount.isSetPassword()){
			return rpcAuthAccount.getPassword();
		}else{
			return null;
		}
	}
	public void setPassword(String password) {
		if(password == null){
			rpcAuthAccount.setPassword(password);
		}
	}
	public Date getExpiredDate() {
		if(rpcAuthAccount.isSetExpiredDate()){
			return new Date(rpcAuthAccount.getExpiredDate());
		}else{
			return null;
		}
	}
	public void setExpiredDate(Date expiredDate) {
		if(expiredDate != null){
			rpcAuthAccount.setExpiredDate(expiredDate.getTime());
		}
	}
	public boolean isLocked() {
		if(rpcAuthAccount.isSetLocked()){
			return rpcAuthAccount.isLocked();
		}else{
			return false;
		}
	}
	public void setLocked(Boolean locked) {
		if(locked != null){
			rpcAuthAccount.setLocked(locked);
		}
	}
	public Date getPasswordExpired() {
		if(rpcAuthAccount.isSetPasswordExpired()){
			return new Date(rpcAuthAccount.getPasswordExpired());
		}else{
			return null;
		}
	}
	public void setPasswordExpired(Date passwordExpired) {
		if(passwordExpired != null){
			rpcAuthAccount.setPasswordExpired(passwordExpired.getTime());
		}
	}
	public boolean isEnabled() {
		if(rpcAuthAccount.isSetEnabled()){
			return rpcAuthAccount.isEnabled();
		}else{
			return false;
		}
	}
	public void setEnabled(Boolean enabled) {
		if(enabled != null){
			rpcAuthAccount.setEnabled(enabled);
		}
	}
	public String getAccountID() {
		if(rpcAuthAccount.isSetAccountID()){
			return rpcAuthAccount.getAccountID();
		}else{
			return null;
		}
	}
	public void setAccountID(String accountID) {
		if(accountID != null){
			rpcAuthAccount.setAccountID(accountID);
		}
	}

	public RpcAuthAccount toRpcObject() {
		return rpcAuthAccount;
	}
}
