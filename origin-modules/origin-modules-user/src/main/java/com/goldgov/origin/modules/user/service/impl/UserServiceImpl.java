package com.goldgov.origin.modules.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.goldgov.origin.modules.user.dao.UserDao;
import com.goldgov.origin.modules.user.service.User;
import com.goldgov.origin.modules.user.service.UserQuery;
import com.goldgov.origin.modules.user.service.UserService;

@Component
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	public void deleteUser(Integer[] ids) {
		userDao.deleteUser(ids);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public User findUser(Integer userID) {
		return userDao.findUser(userID);
	}
	
	@Override
	public List<User> findUserList(UserQuery userQuery) {
		return userDao.findUserListByPage(userQuery);
	}

	@Override
	public User findUserByLoginName(String loginName) {
		return userDao.findUserByLoginName(loginName);
	}

}
