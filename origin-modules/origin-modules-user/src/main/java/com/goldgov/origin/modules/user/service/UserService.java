package com.goldgov.origin.modules.user.service;

import java.util.List;

public interface UserService {

	public void addUser(User user);
	
	public void deleteUser(Integer[] ids);
	
	public void updateUser(User user);
	
	public User findUser(Integer userID);
	
	public List<User> findUserList(UserQuery userQuery);
	
	public User findUserByLoginName(String loginName);
}
