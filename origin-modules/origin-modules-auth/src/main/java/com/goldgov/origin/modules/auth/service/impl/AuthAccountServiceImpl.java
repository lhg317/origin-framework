package com.goldgov.origin.modules.auth.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldgov.origin.modules.auth.dao.AuthenticationDao;
import com.goldgov.origin.modules.auth.service.AuthAccount;
import com.goldgov.origin.modules.auth.service.AuthAccountService;
import com.goldgov.origin.modules.auth.service.PasswordEncoder;

@Service
public class AuthAccountServiceImpl implements AuthAccountService{

	@Autowired
	private AuthenticationDao authenticationDao;
	
	@Autowired(required=false)
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void addAuthAccount(AuthAccount authAccount) {
		authAccount.setPassword(getPasswordEncoder().encode(authAccount.getPassword()));
		authenticationDao.addAuthAccount(authAccount);
	}

	@Override
	public AuthAccount getAuthAccount(String principal, String password) {
		return authenticationDao.getAuthAccount(principal, password);
	}

	@Override
	public void updatePassword(String principal, String oldPass, String newPass) {
		PasswordEncoder encoder = getPasswordEncoder();
		authenticationDao.updatePassword(principal, encoder.encode(oldPass), encoder.encode(newPass));
	}

	@Override
	public void updateExpiredDate(String principal, Date expiredDate) {
		authenticationDao.updateExpiredDate(principal, expiredDate);
	}

	@Override
	public void setEnabled(String principal, boolean enabled) {
		authenticationDao.setEnabled(principal, enabled);
	}

	@Override
	public void setLocked(String principal, boolean locked) {
		authenticationDao.setLocked(principal, locked);
	}

	@Override
	public void deleteAuthAccount(String principal) {
		authenticationDao.deleteAuthAccount(new String[]{principal});
	}

	@Override
	public String getPassword(String principal) {
		return authenticationDao.getPassword(principal);
	}
	
	public PasswordEncoder getPasswordEncoder() {
		if(passwordEncoder == null){
			passwordEncoder = new Md5PasswordEncoder();
		}
		return passwordEncoder;
	}

	@Override
	public void deleteAuthAccount(String[] principal) {
		authenticationDao.deleteAuthAccount(principal);
		
	}

}
