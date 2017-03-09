package com.goldgov.origin.webgate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.goldgov.origin.modules.resource.api.RpcResource;
import com.goldgov.origin.modules.resource.api.RpcResourceService;
import com.goldgov.origin.modules.role.api.RpcRole;
import com.goldgov.origin.modules.role.api.RpcRoleService;
import com.goldgov.origin.modules.user.api.RpcUser;
import com.goldgov.origin.modules.user.api.RpcUserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider ,ApplicationContextAware{//,ApplicationListener<ContextRefreshedEvent> {

//	@Autowired
//	@Qualifier("rpcUserService.Client")
	private RpcUserService.Iface userService;
//	@Autowired
//	@Qualifier("rpcRoleService.Client")
	private RpcRoleService.Iface roleService;
//	@Autowired
//	@Qualifier("rpcResourceService.Client")
	private RpcResourceService.Iface resourceService;
	
	private Map<String,List<String>> roleResourceMap;
	
	private List<RpcResource> roleResourceList;
	
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		initRoleResourceMap();
		
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
	
	public void initRoleResourceMap() {
		/*
		 * 资源路径与资源编码的映射
		 */
		if(roleResourceList == null){
			try {
				roleResourceList = resourceService.getAllResource();
			} catch (TException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 将角色编码与资源编码进行Map对象组装，key为资源编码，value为角色，角色可能会多个（角色资源配置重复的情况下）
		if(roleResourceMap == null){
			roleResourceMap = new HashMap<>();
			try {
				List<Map<String, String>> roleResourceMapList = roleService.findRoleResourceMap();
				for (Map<String, String> map : roleResourceMapList) {
					String roleCode = map.get("roleCode");
					String resourceOperate = map.get("resourceOperate");
					List<String> roleCodeList;
					if(roleResourceMap.containsKey(resourceOperate)){
						roleCodeList = roleResourceMap.get(resourceOperate);
					}else{
						roleCodeList= new ArrayList<>();
						roleResourceMap.put(resourceOperate, roleCodeList);
					}
					roleCodeList.add(roleCode);
				}
			} catch (TException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(roleResourceMap);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		userService = (RpcUserService.Iface) applicationContext.getBean("rpcUserService.Client");
		roleService = (RpcRoleService.Iface) applicationContext.getBean("rpcRoleService.Client");
		resourceService = (RpcResourceService.Iface) applicationContext.getBean("rpcResourceService.Client");
	}

}
