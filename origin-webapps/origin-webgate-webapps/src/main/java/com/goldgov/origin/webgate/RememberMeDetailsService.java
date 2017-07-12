package com.goldgov.origin.webgate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.goldgov.origin.modules.role.api.RpcRole;
import com.goldgov.origin.modules.role.api.RpcRoleService;
import com.goldgov.origin.modules.user.api.RpcUser;
import com.goldgov.origin.modules.user.api.RpcUserService;
import com.goldgov.origin.security.RememberMeUserDetailsService;
import com.goldgov.origin.security.UserToken;

/**
 * 一个“记住我”功能的示例
 * @author LiuHG
 * @version 1.0
 */
//@Component
public class RememberMeDetailsService implements RememberMeUserDetailsService{

	@Autowired
	@Qualifier("rpcUserService.Client")
	private RpcUserService.Iface userService;
	
	@Autowired
	@Qualifier("rpcRoleService.Client")
	private RpcRoleService.Iface roleService;
	
	@Override
	public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
		RpcUser user = null;
		try {
			user = userService.getUserByLoginName(loginName);
			if(user == null){
				throw new UsernameNotFoundException(loginName);
			}
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
		
        UserToken userToken = new UserToken(user.getLoginName(),user.getPassword(),user.getUserName(),grantedAuths);
		return userToken;
	}

}
