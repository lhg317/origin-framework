package com.goldgov.origin.modules.user.service.impl;

import com.goldgov.origin.modules.user.api.RpcUser;
import com.goldgov.origin.modules.user.service.User;

public class ProxyUser extends User{

	private RpcUser user;
	
	public ProxyUser(){
		user = new RpcUser();
	}
	
	public ProxyUser(RpcUser user){
		this.user = user;
	}
	
	public ProxyUser(User _user){
		this();
		if(_user == null){
			user = null;
			return;
		}
		setUserID(_user.getUserID());
		setLoginName(_user.getLoginName());
		setUserName(_user.getUserName());
		setPassword(_user.getPassword());
		setEmail(_user.getEmail());
	}

	public String getUserID() {
		return user.getUserID();
	}

	public void setUserID(String userID) {
		user.setUserID(userID);
	}

	public String getLoginName() {
		return user.getLoginName();
	}

	public void setLoginName(String loginID) {
		user.setLoginName(loginID);
	}

	public String getUserName() {
		return user.getUserName();
	}

	public void setUserName(String userName) {
		user.setUserName(userName);
	}

	public String getPassword() {
		return user.getPassword();
	}

	public void setPassword(String password) {
		user.setPassword(password);
	}

	public String getEmail() {
		return user.getEmail();
	}

	public void setEmail(String email) {
		user.setEmail(email);
	}
	
	public RpcUser toRpcUser(){
		return user;
	}
	
}
