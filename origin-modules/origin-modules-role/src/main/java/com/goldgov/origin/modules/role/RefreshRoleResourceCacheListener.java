package com.goldgov.origin.modules.role;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.goldgov.origin.core.cache.CacheHolder;
import com.goldgov.origin.modules.role.event.SaveRoleResourceEvent;
import com.goldgov.origin.modules.role.service.RoleService;

/**
 * 当修改角色与资源对应关系时，刷新缓存
 * @author LiuHG
 * @version 1.0
 */
@Component
public class RefreshRoleResourceCacheListener implements ApplicationListener<SaveRoleResourceEvent>{

	@Autowired
	private RoleService roleService;
	
	@Override
	public void onApplicationEvent(SaveRoleResourceEvent event) {
		Map<String, List<String>> roleResourceMap = roleService.getRoleResourceMap();
//		FIXME 此处是数据保存在缓存，key与security的ResourceConstants.CACHE_CODE_ROLE_RESOURCE_MAPPING对应
		CacheHolder.put("CACHE_CODE_ROLE_RESOURCE_MAPPING", roleResourceMap);
	}

}
