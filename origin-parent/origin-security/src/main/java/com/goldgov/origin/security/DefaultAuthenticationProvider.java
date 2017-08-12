package com.goldgov.origin.security;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * 默认的登录认证处理器。账户信息配置来自主属性配置文件（每个属性均非必须提供）:<p>
 * <ul>
 * <li>security.user.name 用户登录名，如果不设置则以“user”为登录名</li>
 * <li>security.user.password 用户登录密码（明文），如果不设置则随机密码（日志级别为INFO级别时，在启动过程中会输出密码值）</li>
 * <li>security.user.display-name 用户显示名，不设置则以登录名代替</li>
 * <li>security.user.role 用户角色，多个以“,”分隔</li>
 * </ul>
 * @author LiuHG
 * @version 1.0
 */
public class DefaultAuthenticationProvider implements AuthenticationProvider,InitializingBean {

	private final Log logger = LogFactory.getLog(getClass());
	
	@Value("${security.user.name:user}")
	private String userName;
	
	@Value("${security.user.password:}")
	private String defaultPassword;
	
	@Value("${security.user.display-name:}")
	private String displayName;
	
	@Value("${security.user.role:}")
	private String[] userRoles;
	
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String loginName = (String) authentication.getPrincipal();
        String password = (String)authentication.getCredentials();
        
        if(loginName.equals(userName) && password.equals(defaultPassword)){
        	List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority("IS_AUTHENTICATED_ANONYMOUSLY"));
            for (String roleCode : userRoles) {
            	grantedAuths.add(new SimpleGrantedAuthority(roleCode));
			}
            
        	UserToken userToken = new UserToken(loginName,loginName, password, displayName,grantedAuths);
        	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userToken, password, grantedAuths);
    		return authenticationToken;
        }

        throw new BadCredentialsException("认证失败：" + loginName);
        
	}

	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(defaultPassword == null || "".equals(defaultPassword)){
			defaultPassword = UUID.randomUUID().toString().replaceAll("-","");
			logger.info("使用默认密码：" + defaultPassword );
		}
		
		if(displayName  == null || "".equals(displayName)){
			displayName = userName;
		}
	}

}
