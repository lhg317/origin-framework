package com.goldgov.origin.webgate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

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

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	@Qualifier("rpcUserService.Client")
	private RpcUserService.Iface userService;
	@Autowired
	@Qualifier("rpcRoleService.Client")
	private RpcRoleService.Iface roleService;
	
	private Map<String,String> roleResourceMap = new HashMap<>();
	
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String loginName = (String) authentication.getPrincipal();
        String password = (String)authentication.getCredentials();
        
        try {
			RpcUser user = userService.findUserByLoginName(loginName);
			List<RpcRole> roleList = roleService.findRoleByObject(loginName);
			if(user != null){
				System.out.println(user.getUserName() + " role num:" + roleList.size());
			}
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        grantedAuths.add(new SimpleGrantedAuthority("IS_AUTHENTICATED_ANONYMOUSLY"));
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        if(loginName.equals("liuhg") && password.equals("111111")){
        	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginName, password, grantedAuths);
        	return authenticationToken;
        }

        throw new BadCredentialsException("认证失败：" + loginName);
        
	}

	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
	@PostConstruct
	public void initRoleResourceMap(){
		/*
		 * 前置：扫描路径时，资源路径与资源编码的映射
		 */
		
		/*
		 * 1 查询所有角色及对应的角色资源对象
		 * 2 将角色编码与资源编码进行Map对象组装，key为资源编码，value为角色，角色可能会多个（角色资源配置重复的情况下）
		 * */
	}

}
