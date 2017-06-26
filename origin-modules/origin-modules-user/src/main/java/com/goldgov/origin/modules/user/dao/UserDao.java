package com.goldgov.origin.modules.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldgov.origin.core.dao.Mapper;
import com.goldgov.origin.modules.user.service.Group;
import com.goldgov.origin.modules.user.service.User;
import com.goldgov.origin.modules.user.service.UserQuery;

@Mapper
public interface UserDao {

	public void addUser(User user);
	
	public void deleteUser(@Param("ids") String[] userIDs);
	
	public void updateUser(User user);
	
	public User getUser(String userID);
	
	public List<User> listUser(UserQuery userQuery);
	
	public User getUserByLoginName(@Param("loginName") String loginName);
	
	public void updatePassword(@Param("loginName")String loginName,@Param("oldPassword") String oldPassword,@Param("newPassword") String newPassword);
	
	public void addGroup(Group group);
	
	public void addUser(User user,String groupID);
	
	public void moveUser(List<User> userList,String groupID);
	
	public void deleteGroup(String[] ids);
	
	public void updateGroupName(String id,String groupName);
}
