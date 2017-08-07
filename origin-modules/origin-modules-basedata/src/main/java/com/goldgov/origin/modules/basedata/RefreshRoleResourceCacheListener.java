package com.goldgov.origin.modules.basedata;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.goldgov.origin.core.cache.CacheHolder;
import com.goldgov.origin.modules.basedata.event.DataModificationEvent;
import com.goldgov.origin.modules.basedata.service.BaseDataService;

/**
 * @author LiuHG
 * @version 1.0
 */
@Component
public class RefreshRoleResourceCacheListener implements ApplicationListener<DataModificationEvent>{

	@Autowired
	private BaseDataService baseDataService;
	
	@Override
	public void onApplicationEvent(DataModificationEvent event) {
//		Map<String, List<String>> roleResourceMap = roleService.getRoleResourceMap();
////		FIXME 此处是数据保存在缓存，key与security的ResourceConstants.CACHE_CODE_ROLE_RESOURCE_MAPPING对应
//		CacheHolder.put("CACHE_CODE_ROLE_RESOURCE_MAPPING", roleResourceMap);
	}

}
