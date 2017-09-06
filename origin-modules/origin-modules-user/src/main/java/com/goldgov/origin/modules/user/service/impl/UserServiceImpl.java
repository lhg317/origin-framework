package com.goldgov.origin.modules.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.goldgov.origin.modules.user.dao.UserDao;
import com.goldgov.origin.modules.user.event.AddUserEvent;
import com.goldgov.origin.modules.user.event.UpdateUserEvent;
import com.goldgov.origin.modules.user.event.UserEvent;
import com.goldgov.origin.modules.user.exception.UserExistException;
import com.goldgov.origin.modules.user.exception.UserNameCheckFailException;
import com.goldgov.origin.modules.user.service.Group;
import com.goldgov.origin.modules.user.service.User;
import com.goldgov.origin.modules.user.service.UserNameChecker;
import com.goldgov.origin.modules.user.service.UserQuery;
import com.goldgov.origin.modules.user.service.UserService;

@Component
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired(required=false)
	private List<UserEvent> events;
	
	@Autowired(required=false)
	private UserNameChecker userNameChecker;
	
	@Override
	public void addUser(User user) throws UserExistException, UserNameCheckFailException {
		if(existUser(user.getLoginName())){
			throw new UserExistException(user.getLoginName());
		}
		if(!checkUserName(user.getUserName())){
			throw new UserNameCheckFailException(user.getLoginName());
		}
		
		processBeforeEvent(AddUserEvent.class,user);
		userDao.addUser(user);
		processAfterEvent(AddUserEvent.class,user);
	}
	
	@Override
	public void deleteUser(String[] ids) {
		userDao.deleteUser(ids);
		throw new RuntimeException();
	}

	@Override
	public void updateUser(User user) throws UserNameCheckFailException {
		if(!checkUserName(user.getUserName())){
			throw new UserNameCheckFailException(user.getLoginName());
		}
		processBeforeEvent(UpdateUserEvent.class,user);
		userDao.updateUser(user);
		processAfterEvent(UpdateUserEvent.class,user);
	}

	@Override
	public User getUser(String userID) {
		return userDao.getUser(userID);
	}
	
	@Override
	public List<User> listUser(UserQuery userQuery) {
		return userDao.listUser(userQuery);
	}

	@Override
	public User getUserByLoginName(String loginName) {
		return userDao.getUserByLoginName(loginName);
	}

	@Override
	public boolean existUser(String loginName) {
		User user = getUserByLoginName(loginName);
		return user != null;
	}

	@Override
	public boolean checkUserName(String userName) {
		if(userNameChecker == null){
			return true;
		}else{
			return userNameChecker.doCheck(userName);
		}
	}
	
	private void processBeforeEvent(Class<? extends UserEvent> eventClass,User obj) {
		if(events == null || events.isEmpty()){
			return;
		}
		for (UserEvent userEvent : events) {
			if(eventClass.isAssignableFrom(userEvent.getClass())){
				userEvent.onBeforeEvent(obj);
			}
		}
	}
	
	private void processAfterEvent(Class<? extends UserEvent> eventClass,User obj) {
		if(events == null || events.isEmpty()){
			return;
		}
		for (UserEvent userEvent : events) {
			if(eventClass.isAssignableFrom(userEvent.getClass())){
				userEvent.onAfterEvent(obj);
			}
		}
	}

	@Override
	public void addGroup(Group group) {
		userDao.addGroup(group);
	}

	@Override
	public void addUser(User user, String groupID) {
		userDao.addUser(user,groupID);
	}

	@Override
	public void moveUser(List<User> userList, String groupID) {
		userDao.moveUser(userList, groupID);
	}

	@Override
	public void deleteGroup(String[] ids) {
		userDao.deleteGroup(ids);
	}

	@Override
	public void updateGroupName(String id, String groupName) {
		userDao.updateGroupName(id, groupName);
	}

}
