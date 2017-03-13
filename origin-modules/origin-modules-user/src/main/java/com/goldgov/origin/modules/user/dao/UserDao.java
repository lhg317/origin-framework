package com.goldgov.origin.modules.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldgov.origin.core.dao.Mapper;
import com.goldgov.origin.modules.user.service.User;
import com.goldgov.origin.modules.user.service.UserQuery;

@Mapper
public interface UserDao {

	public void addUser(User user);
	
	public void deleteUser(@Param("userIDs") Integer[] userIDs);
	
	public void updateUser(User user);
	
	public User findUser(Integer userID);
	
	public List<User> findUsers(UserQuery userQuery);
	
	public User findUserByLoginName(@Param("loginName") String loginName);
	
	public void updatePassword(@Param("loginName")String loginName,@Param("oldPassword") String oldPassword,@Param("newPassword") String newPassword);
}
