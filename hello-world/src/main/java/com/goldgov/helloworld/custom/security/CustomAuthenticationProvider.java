package com.goldgov.helloworld.custom.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.goldgov.origin.modules.auth.api.PasswordEncoder;
import com.goldgov.origin.modules.auth.api.RpcAuthAccountService;
import com.goldgov.origin.modules.auth.api.impl.Md5PasswordEncoder;
import com.goldgov.origin.modules.role.api.RpcRole;
import com.goldgov.origin.modules.role.api.RpcRoleService;
import com.goldgov.origin.modules.user.api.RpcUser;
import com.goldgov.origin.modules.user.api.RpcUserService;
import com.goldgov.origin.security.UserToken;
import com.goldgov.origin.security.authentication.BaseAuthenticationProvider;

@Component
public class CustomAuthenticationProvider extends BaseAuthenticationProvider{//implements OriginAuthenticationProvider {

	@Autowired
	@Qualifier("rpcUserService.Client")
	private RpcUserService.Iface userService;
	
	@Autowired
	@Qualifier("rpcAuthAccountService.Client")
	private RpcAuthAccountService.Iface authAccountService;
	
	@Autowired
	@Qualifier("rpcRoleService.Client")
	private RpcRoleService.Iface roleService;
	
	private PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

	@Override
	protected boolean authenticate(String loginName, String password) throws Exception {
		String userPwd = authAccountService.getPassword(loginName);
    	password = passwordEncoder.encode(password);
		if(userPwd == null || !password.equals(userPwd)){
			return false;
		}
		return true;
	}

	@Override
	protected UserToken getUserToken(String loginName, String password) {
      RpcUser user = null;
      try {
			user = userService.getUserByLoginName(loginName);
		} catch (Exception e) {
			throw new RuntimeException("获取用户信息时出现错误：" + loginName,e);
		}
      
      List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
      grantedAuths.add(new SimpleGrantedAuthority("IS_AUTHENTICATED_ANONYMOUSLY"));
      
		try {
			List<RpcRole> roleList = roleService.listRoleByObject(loginName);
	        for (RpcRole role : roleList) {
	        	grantedAuths.add(new SimpleGrantedAuthority(role.getRoleCode()));
			}
		} catch (Exception e) {
			throw new RuntimeException("获取用户角色时出现错误：" + loginName,e);
		}
		
		UserToken userToken = new UserToken(loginName, password, user.getUserName(), grantedAuths);
		return userToken;
	}
}
