package com.goldgov.origin.modules.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldgov.origin.core.discovery.rpc.RpcService;
import com.goldgov.origin.modules.user.api.RpcUser;
import com.goldgov.origin.modules.user.api.RpcUserExistException;
import com.goldgov.origin.modules.user.api.RpcUserNameCheckFailException;
import com.goldgov.origin.modules.user.api.RpcUserQuery;
import com.goldgov.origin.modules.user.api.RpcUserService;
import com.goldgov.origin.modules.user.exception.UserExistException;
import com.goldgov.origin.modules.user.exception.UserNameCheckFailException;
import com.goldgov.origin.modules.user.service.User;
import com.goldgov.origin.modules.user.service.UserService;

@RpcService
public class RpcUserServiceImpl implements RpcUserService.Iface{

	@Autowired
	private UserService userService;
	
	@Override
	public void addUser(RpcUser user) throws TException {
		try {
			userService.addUser(new ProxyUser(user));
		} catch (UserExistException e) {
			throw new RpcUserExistException(user.getLoginName());
		} catch (UserNameCheckFailException e) {
			throw new RpcUserNameCheckFailException(user.getUserName());
		}
	}

	@Override
	public void deleteUser(List<String> ids) throws TException {
		userService.deleteUser(ids.toArray(new String[0]));
	}

	@Override
	public void updateUser(RpcUser user) throws TException {
		userService.updateUser(new ProxyUser(user));
	}

	@Override
	public RpcUser getUser(String userID) throws TException {
		User user = userService.getUser(userID);
		return new ProxyUser(user).toRpcUser();
	}

	@Override
	public RpcUserQuery listUser(RpcUserQuery userQuery) throws TException {
		List<User> findUserList = userService.listUser(new ProxyUserQuery(userQuery));
		List<RpcUser> resultList = new ArrayList<>();
		for (User user : findUserList) {
			resultList.add(new ProxyUser(user).toRpcUser());
		}
		userQuery.setResultList(resultList);
		return userQuery;
	}

	@Override
	public RpcUser getUserByLoginName(String loginName) throws TException {
		User user = userService.getUserByLoginName(loginName);
		return new ProxyUser(user).toRpcUser();
	}

	@Override
	public boolean existUser(String loginName) throws TException {
		return userService.existUser(loginName);
	}
	
	@Override
	public boolean checkUserName(String userName) throws TException {
		return userService.checkUserName(userName);
	}

	@Override
	public boolean updatePassword(String loginName, String oldPassword, String newPassword) throws TException {
		// TODO Auto-generated method stub
		return false;
	}

}
