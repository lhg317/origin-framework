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
import com.goldgov.origin.modules.user.service.PasswordEncoder;
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
	
	@Autowired(required=false)
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void addUser(User user) throws UserExistException, UserNameCheckFailException {
		if(existUser(user.getLoginName())){
			throw new UserExistException(user.getLoginName());
		}
		if(userNameChecker != null && !userNameChecker.doCheck(user.getUserName())){
			throw new UserNameCheckFailException(user.getLoginName());
		}
		
		user.setPassword(getPasswordEncoder().encode(user.getPassword()));
		
		processBeforeEvent(AddUserEvent.class,user);
		userDao.addUser(user);
		processAfterEvent(AddUserEvent.class,user);
	}
	
	@Override
	public void deleteUsers(Integer[] ids) {
		userDao.deleteUser(ids);
	}

	@Override
	public void updateUser(User user) {
		processBeforeEvent(UpdateUserEvent.class,user);
		userDao.updateUser(user);
		processAfterEvent(UpdateUserEvent.class,user);
	}

	@Override
	public User findUserByID(Integer userID) {
		return userDao.findUser(userID);
	}
	
	@Override
	public List<User> findUsers(UserQuery userQuery) {
		return userDao.findUsers(userQuery);
	}

	@Override
	public User findUserByLoginName(String loginName) {
		return userDao.findUserByLoginName(loginName);
	}

	@Override
	public boolean existUser(String loginName) {
		User user = findUserByLoginName(loginName);
		return user != null;
	}

	@Override
	public boolean checkUserName(String userName) {
		// TODO Auto-generated method stub
		return true;
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

	public PasswordEncoder getPasswordEncoder() {
		if(passwordEncoder == null){
			passwordEncoder = new Md5PasswordEncoder();
		}
		return passwordEncoder;
	}

	@Override
	public void updatePassword(String loginName, String oldPassword, String newPassword) {
		PasswordEncoder encoder = getPasswordEncoder();
		userDao.updatePassword(loginName, encoder.encode(oldPassword), encoder.encode(newPassword));
	}
	

}
