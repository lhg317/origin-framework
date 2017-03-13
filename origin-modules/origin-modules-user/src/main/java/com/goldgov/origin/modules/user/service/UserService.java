package com.goldgov.origin.modules.user.service;

import java.util.List;

import com.goldgov.origin.modules.user.exception.UserExistException;
import com.goldgov.origin.modules.user.exception.UserNameCheckFailException;

public interface UserService {

	public void addUser(User user) throws UserExistException, UserNameCheckFailException;
	
	public void deleteUsers(Integer[] ids);
	
	public void updateUser(User user);
	
	public User findUserByID(Integer userID);
	
	public User findUserByLoginName(String loginName);

	public List<User> findUsers(UserQuery userQuery);

	public boolean existUser(String loginName);
	
	public boolean checkUserName(String userName);
	
}
