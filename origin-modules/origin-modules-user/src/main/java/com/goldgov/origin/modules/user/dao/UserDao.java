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
	
	public User findUserByID(Integer userID);
	
	public List<User> findUserListByPage(UserQuery userQuery);
}
