package com.goldgov.origin.modules.role;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.goldgov.origin.core.cache.CacheHolder;
import com.goldgov.origin.modules.role.event.SaveRoleResourceEvent;
import com.goldgov.origin.modules.role.service.RoleService;

@Component
public class RefreshRoleResourceCacheListener implements ApplicationListener<SaveRoleResourceEvent>{

	@Autowired
	private RoleService roleService;
	
	@Override
	public void onApplicationEvent(SaveRoleResourceEvent event) {
		Map<String, List<String>> roleResourceMap = roleService.getRoleResourceMap();
//		FIXME
		CacheHolder.put("CACHE_CODE_ROLE_RESOURCE_MAPPING", roleResourceMap);
	}

}
