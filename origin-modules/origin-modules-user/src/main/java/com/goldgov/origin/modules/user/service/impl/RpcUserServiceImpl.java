package com.goldgov.origin.modules.user.service.impl;

import java.util.List;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldgov.origin.core.discovery.rpc.ResultSetUtils;
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
	
	private UserConverter userConverter = new UserConverter();
	
	@Override
	public String addUser(RpcUser user) throws TException {
		try {
			userService.addUser(userConverter.fromRpcObject(user));
			return user.getUserID();
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
		try {
			userService.updateUser(userConverter.fromRpcObject(user));
		} catch (UserNameCheckFailException e) {
			throw new RpcUserNameCheckFailException(user.getUserName());
		}
		
	}

	@Override
	public RpcUser getUser(String userID) throws TException {
		User user = userService.getUser(userID);
		return userConverter.toRpcObject(user);
	}

	@Override
	public RpcUserQuery listUser(RpcUserQuery userQuery) throws TException {
		List<User> findUserList = userService.listUser(new ProxyUserQuery(userQuery));
		userQuery.setResultList(ResultSetUtils.convertToRpc(findUserList, userConverter));
		return userQuery;
	}

	@Override
	public RpcUser getUserByLoginName(String loginName) throws TException {
		User user = userService.getUserByLoginName(loginName);
		return userConverter.toRpcObject(user);
	}

	@Override
	public boolean existUser(String loginName) throws TException {
		return userService.existUser(loginName);
	}
	
	@Override
	public boolean checkUserName(String userName) throws TException {
		return userService.checkUserName(userName);
	}

}
