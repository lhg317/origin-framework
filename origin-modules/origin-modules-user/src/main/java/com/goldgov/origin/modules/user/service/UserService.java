package com.goldgov.origin.modules.user.service;

import java.util.List;

import com.goldgov.origin.modules.user.exception.UserExistException;
import com.goldgov.origin.modules.user.exception.UserNameCheckFailException;

public interface UserService {

	public void addUser(User user) throws UserExistException, UserNameCheckFailException;
	
	public void deleteUser(String[] ids);
	
	public void updateUser(User user);
	
	public User getUser(String userID);
	
	public User getUserByLoginName(String loginName);

	public List<User> listUser(UserQuery userQuery);

	public boolean existUser(String loginName);
	
	public boolean checkUserName(String userName);
	
	public void updatePassword(String loginName, String oldPassword, String newPassword);
	
}

