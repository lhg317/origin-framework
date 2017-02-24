package com.goldgov.origin.modules.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldgov.origin.core.discovery.rpc.RpcService;
import com.goldgov.origin.modules.user.api.ProxyUser;
import com.goldgov.origin.modules.user.api.ProxyUserQuery;
import com.goldgov.origin.modules.user.api.RpcUser;
import com.goldgov.origin.modules.user.api.RpcUserQuery;
import com.goldgov.origin.modules.user.api.RpcUserService;
import com.goldgov.origin.modules.user.service.User;
import com.goldgov.origin.modules.user.service.UserService;

@RpcService
public class RpcUserServiceImpl implements RpcUserService.Iface{

	@Autowired
	private UserService userService;
	
	@Override
	public void addUser(RpcUser user) throws TException {
		userService.addUser(new ProxyUser(user));
	}

	@Override
	public void deleteUser(List<Integer> ids) throws TException {
		userService.deleteUser(ids.toArray(new Integer[0]));
	}

	@Override
	public void updateUser(RpcUser user) throws TException {
		userService.updateUser(new ProxyUser(user));
	}

	@Override
	public RpcUser findUserByID(int userID) throws TException {
		User user = userService.findUserByID(userID);
		return new ProxyUser(user).toRpcUser();
	}

	@Override
	public RpcUserQuery findUserList(RpcUserQuery userQuery) throws TException {
		List<User> findUserList = userService.findUserList(new ProxyUserQuery(userQuery));
		List<RpcUser> resultList = new ArrayList<>();
		for (User user : findUserList) {
			resultList.add(new ProxyUser(user).toRpcUser());
		}
		userQuery.setResultList(resultList);
		return userQuery;
	}

}
