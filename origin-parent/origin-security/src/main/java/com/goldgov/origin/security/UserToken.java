package com.goldgov.origin.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 用户表示对象，对象中可以获取用户名，登录名及角色编码。<p>
 * <b>说明：由于该对象继承了Spring Security的{@link org.springframework.security.core.userdetails.User User}对象，该对象中
 * 存在getUsername()方法用于获取登录名，而本类中提供的getUserName()方法（一个字母大小写之差）是用来获取用户显示名，本类中使用
 * getLoginName()方法来获取登录名。在使用时请注意</b>
 * @author LiuHG
 * @version 1.0
 */
public class UserToken extends User implements UserDelegate{

	private static final long serialVersionUID = 3512951721391711866L;
	
	private final String userID;
	private final String loginName;
	private final String userName;
	
	private final String[] roles;

	
	public UserToken(String userID,String loginName, String password,String userName) {
		super(loginName, password,new ArrayList<GrantedAuthority>());
		this.userID = userID;
		this.loginName = loginName;
		this.userName = userName;
		roles = new String[0];
	}
	
	/**
	 * 登录成功后的用户对象
	 * @param loginName 登录名
	 * @param password 密码
	 * @param userName 用户显示名
	 * @param authorities 用户角色编码，由SimpleGrantedAuthority对象组成的集合
	 */
	public UserToken(String userID,String loginName, String password,String userName,  List<GrantedAuthority> authorities) {
		super(loginName, password,authorities);
		this.userID = userID;
		this.loginName = loginName;
		this.userName = userName;
		roles = new String[authorities.size()];
		for (int i = 0; i < authorities.size(); i++) {
			roles[i] = authorities.get(i).getAuthority();
		}
	}

	public String getUserName() {
		return userName;
	}

	@Override
	public String getLoginName() {
		return loginName;
	}

	@Override
	public String[] getRoles() {
		return roles;
	}

	@Override
	public String getUserID() {
		return userID;
	}

}
