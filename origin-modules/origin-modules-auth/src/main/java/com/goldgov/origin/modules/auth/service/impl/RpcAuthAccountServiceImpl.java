package com.goldgov.origin.modules.auth.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldgov.origin.core.discovery.rpc.RpcService;
import com.goldgov.origin.modules.auth.api.RpcAuthAccount;
import com.goldgov.origin.modules.auth.api.RpcAuthAccountService;
import com.goldgov.origin.modules.auth.service.AuthAccount;
import com.goldgov.origin.modules.auth.service.AuthAccountService;

@RpcService
public class RpcAuthAccountServiceImpl implements RpcAuthAccountService.Iface{

	@Autowired
	private AuthAccountService authAccountService;
	
	private AuthAccountConverter converter = new AuthAccountConverter();
	
	@Override
	public void addAuthAccount(RpcAuthAccount authAccount) throws TException {
		authAccountService.addAuthAccount(converter.fromRpcObject(authAccount));
	}

	@Override
	public RpcAuthAccount getAuthAccount(String principal, String password) throws TException {
		AuthAccount authAccount = authAccountService.getAuthAccount(principal, password);
		return converter.toRpcObject(authAccount);
	}

	@Override
	public void updatePassword(String principal, String oldPass, String newPass) throws TException {
		authAccountService.updatePassword(principal, oldPass, newPass);
	}

	@Override
	public void updateExpiredDate(String principal, long expiredDate) throws TException {
		authAccountService.updateExpiredDate(principal, new Date(expiredDate));
	}

	@Override
	public void setEnabled(String principal, boolean enabled) throws TException {
		authAccountService.setEnabled(principal, enabled);
	}

	@Override
	public void setLocked(String principal, boolean locked) throws TException {
		authAccountService.setLocked(principal, locked);
	}

	@Override
	public void deleteAuthAccount(List<String> principal) throws TException {
		authAccountService.deleteAuthAccount(principal.toArray(new String[0]));
	}

	@Override
	public String getPassword(String principal) throws TException {
		return authAccountService.getPassword(principal);
	}

}
