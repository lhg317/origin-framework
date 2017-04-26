package com.goldgov.origin.webgate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.goldgov.origin.modules.role.api.RpcRole;
import com.goldgov.origin.modules.role.api.RpcRoleService;
import com.goldgov.origin.modules.user.api.RpcUser;
import com.goldgov.origin.modules.user.api.RpcUserService;
import com.goldgov.origin.security.UserToken;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	@Qualifier("rpcUserService.Client")
	private RpcUserService.Iface userService;
	
	@Autowired
	@Qualifier("rpcRoleService.Client")
	private RpcRoleService.Iface roleService;
	
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		String loginName = (String) authentication.getPrincipal();
        String password = (String)authentication.getCredentials();
        
        try {
			RpcUser user = userService.getUserByLoginName(loginName);
			
			password = DigestUtils.md5Hex(password);
			if(user == null || !password.equals(user.getPassword())){
				throw new BadCredentialsException("认证失败：" + loginName);
			}
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        grantedAuths.add(new SimpleGrantedAuthority("IS_AUTHENTICATED_ANONYMOUSLY"));
//        grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        
		try {
			List<RpcRole> roleList = roleService.listRoleByObject(loginName);
	        for (RpcRole role : roleList) {
	        	grantedAuths.add(new SimpleGrantedAuthority(role.getRoleCode()));
			}
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserToken authenticationToken = new UserToken(loginName, password, grantedAuths);
		authenticationToken.setUserName("测试用户");
		return authenticationToken;
        
//        if(loginName.equals("liuhg") && password.equals("111111")){
//        	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginName, password, grantedAuths);
//        	return authenticationToken;
//        }
//
//        throw new BadCredentialsException("认证失败：" + loginName);
        
	}

	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
}
