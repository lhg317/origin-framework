package com.goldgov.origin.modules.user.service;

import java.util.List;

import com.goldgov.origin.modules.user.exception.UserExistException;
import com.goldgov.origin.modules.user.exception.UserNameCheckFailException;

/**
 * 用户管理接口
 * @author LiuHG
 *
 */
public interface UserService {

	/**
	 * 添加用户，检查登录名是否存在同时用户名是否含有敏感字（默认不检查敏感字，敏感字检查需要创建{@link com.goldgov.origin.modules.user.service.UserNameChecker UserNameChecker}的Spring的Bean），密码会以MD5方式进行编码，编码方式可以通过定义实现{@link com.goldgov.origin.modules.user.service.PasswordEncoder PasswordEncoder}接口
	 * 的Spring Bean来替换。<p>
	 * 用户添加前、后可以通过创建{@link com.goldgov.origin.modules.user.event.UserEvent UserEvent}的实现类并声明为Spring的Bean实例来扩展添加前、后的事件。
	 * @param user 预添加的用户对象，不能为null，添加时不必提供主键值
	 * @throws UserExistException 用户登录名存在时抛出
	 * @throws UserNameCheckFailException 用户名包含敏感字符时抛出（默认不进行敏感字检查）
	 */
	public void addUser(User user) throws UserExistException, UserNameCheckFailException;
	
	public void deleteUser(String[] ids);
	
	public void updateUser(User user) throws UserNameCheckFailException ;
	
	public User getUser(String userID);
	
	public User getUserByLoginName(String loginName);

	public List<User> listUser(UserQuery userQuery);

	public boolean existUser(String loginName);
	
	public boolean checkUserName(String userName);
	
	public void updatePassword(String loginName, String oldPassword, String newPassword);
	
	public void addGroup(Group group);
	
	public void addUser(User user,String groupID);
	
	public void moveUser(List<User> userList,String groupID);
	
	public void deleteGroup(String[] ids);
	
	public void updateGroupName(String id,String groupName);
	
}

