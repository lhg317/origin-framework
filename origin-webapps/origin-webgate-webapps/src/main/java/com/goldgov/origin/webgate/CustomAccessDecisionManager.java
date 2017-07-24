package com.goldgov.origin.webgate;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import com.goldgov.origin.core.cache.CacheHolder;
import com.goldgov.origin.modules.role.api.RpcRoleService;
import com.goldgov.origin.security.resource.ResourceConstants;
import com.goldgov.origin.security.resource.ResourceContext;

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager{

	public static final String CACHE_CODE_PATH_RESOURCE_MAPPING = "CACHE_CODE_PATH_RESOURCE_MAPPING";

	@Autowired
	@Qualifier("rpcRoleService.Client")
	private RpcRoleService.Iface roleService;
	
	private boolean initialized; 
	
	@SuppressWarnings("unchecked")
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while(iterator.hasNext()){
			ConfigAttribute cfgAttribute = iterator.next();
			if(!cfgAttribute.toString().equals("authenticated")){//在ExpressionUrlAuthorizationConfigurer中定义的
				return;//不需要授权的请求，直接返回
			}
		}
		
		FilterInvocation filterInvocation = (FilterInvocation)object;
		HttpServletRequest httpRequest = filterInvocation.getHttpRequest();
		
		if(authentication instanceof AnonymousAuthenticationToken){
			throw new AccessDeniedException("拒绝以匿名身份访问："+filterInvocation.getFullRequestUrl());
		}
		
		synchronized (this) {
			if(!initialized){
				initRoleResourceMap();
				initialized = true;
			}
		}
		
		Map<String,String> pathMapping = (Map<String, String>) CacheHolder.get(CACHE_CODE_PATH_RESOURCE_MAPPING);
		Map<String,List<String>> roleResourceMapping = (Map<String, List<String>>) CacheHolder.get(ResourceConstants.CACHE_CODE_ROLE_RESOURCE_MAPPING);
		
		String requestURI = httpRequest.getRequestURI();
		
		//根据请求的uri得到对应的资源编码
		String resourceCode = pathMapping.get(requestURI);
		//根据得到的资源编码获取到允许的角色编码集合
		List<String> roleCodeList = null;
		if(resourceCode != null){
			roleCodeList = roleResourceMapping.get(resourceCode);
		}
//		System.out.println("路径：" + requestURI + ",resourceCode：" + resourceCode + ",roleCode：" + roleCodeList);
		//检查当前用户是否含有资源访问的角色编码，authority.getAuthority()中即为当前用户所拥有的角色（编码）
		for(GrantedAuthority authority : (Collection<GrantedAuthority>)authentication.getAuthorities()){
			if("ROLE_ANONYMOUS".equals(authority.getAuthority())){
				throw new AccessDeniedException("以ROLE_ANONYMOUS角色访问被拒绝："+filterInvocation.getFullRequestUrl());
			}else if("ROLE_ADMIN".equals(authority.getAuthority())){
				return;
			}else if(roleCodeList != null && roleCodeList.contains(authority.getAuthority())){
				return;
			}

		}
		if(resourceCode != null){
			throw new AccessDeniedException("访问拒绝："+filterInvocation.getFullRequestUrl());
		}
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	
	public void initRoleResourceMap() {
		/*
		 * 资源路径与资源编码的映射
		 */
		Map<String,String> allResourceMapping = ResourceContext.getAllResourcesMap();
		CacheHolder.put(CACHE_CODE_PATH_RESOURCE_MAPPING, allResourceMapping);
		
		// 将角色编码与资源编码进行Map对象组装，key为资源编码，value为角色，角色可能会多个（角色资源配置重复的情况下）
		try {
			roleService.refreshRoleResourceCache();
		} catch (Exception e) {
			throw new RuntimeException("初始化角色资源缓存时失败", e);
		}
	}
	
}
