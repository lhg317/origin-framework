package com.goldgov.origin.webgate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.goldgov.origin.modules.auth.api.RpcAuthAccountService;
import com.goldgov.origin.modules.role.api.RpcRole;
import com.goldgov.origin.modules.role.api.RpcRoleService;
import com.goldgov.origin.modules.user.api.RpcUser;
import com.goldgov.origin.modules.user.api.RpcUserService;
import com.goldgov.origin.security.CustomAuthenticationProvider;
import com.goldgov.origin.security.UserToken;

@Component
public class MyAuthenticationProvider implements CustomAuthenticationProvider {

	@Autowired
	@Qualifier("rpcUserService.Client")
	private RpcUserService.Iface userService;
	
	@Autowired
	@Qualifier("rpcAuthAccountService.Client")
	private RpcAuthAccountService.Iface authAccountService;
	
	@Autowired
	@Qualifier("rpcRoleService.Client")
	private RpcRoleService.Iface roleService;
	
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		String loginName = (String) authentication.getPrincipal();
        String password = (String)authentication.getCredentials();
        
        try {
        	String userPwd = authAccountService.getPassword(loginName);
        	password = DigestUtils.md5Hex(password);
			if(userPwd == null || !password.equals(userPwd)){
				throw new BadCredentialsException("认证失败：" + loginName);
			}
        } catch (Exception e) {
			throw new RuntimeException("获取用户认证信息时出现错误：" + loginName,e);
        }
        
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
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userToken, password, grantedAuths);
		return authenticationToken;
        
	}

	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
}
