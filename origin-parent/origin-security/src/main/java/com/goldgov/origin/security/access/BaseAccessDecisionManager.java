package com.goldgov.origin.security.access;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import com.goldgov.origin.core.cache.CacheHolder;
import com.goldgov.origin.security.resource.ResourceConstants;
import com.goldgov.origin.security.resource.ResourceContext;

public abstract class BaseAccessDecisionManager implements AccessDecisionManager{

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
				Map<String, List<String>> roleResourceMap;
				try {
					roleResourceMap = roleResourceMap();
				} catch (Exception e) {
					throw new RuntimeException("获取角色资源映射关系时出现错误",e);
				}
				CacheHolder.put(ResourceConstants.CACHE_CODE_ROLE_RESOURCE_MAPPING, roleResourceMap);
				initialized = true;
			}
		}
		
		Map<String,String> pathMapping = ResourceContext.getAllResourcesMap();
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
		String adminRole = adminRole();
		for(GrantedAuthority authority : (Collection<GrantedAuthority>)authentication.getAuthorities()){
			if("ROLE_ANONYMOUS".equals(authority.getAuthority())){
				throw new AccessDeniedException("以ROLE_ANONYMOUS角色访问被拒绝："+filterInvocation.getFullRequestUrl());
			}else if(adminRole != null && adminRole.equals(authority.getAuthority())){
				return;
			}else if(roleCodeList != null && roleCodeList.contains(authority.getAuthority())){
				return;
			}

		}
		if(resourceCode != null){
			throw new AccessDeniedException("访问拒绝："+filterInvocation.getFullRequestUrl());
		}
		
	}

	protected abstract Map<String, List<String>> roleResourceMap()throws Exception;
	
	/**
	 * 管理员角色，如果不设置管理员角色则返回null，默认返回null。<br>
	 * 注：管理员角色有权访问任何受限资源
	 * @return 
	 */
	protected String adminRole(){
		return null;
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
