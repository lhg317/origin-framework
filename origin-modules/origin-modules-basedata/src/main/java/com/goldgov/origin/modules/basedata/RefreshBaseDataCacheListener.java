package com.goldgov.origin.modules.basedata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.goldgov.origin.core.cache.CacheHolder;
import com.goldgov.origin.modules.basedata.event.BaseDataEvent;
import com.goldgov.origin.modules.basedata.event.BaseDataEvent.EventType;
import com.goldgov.origin.modules.basedata.service.BaseData;
import com.goldgov.origin.modules.basedata.service.BaseDataService;

/**
 * @author LiuHG
 * @version 1.0
 */
@Component
public class RefreshBaseDataCacheListener implements ApplicationListener<BaseDataEvent>{

	@Autowired
	private BaseDataService baseDataService;
	
	@SuppressWarnings("unchecked")
	@Override
	public void onApplicationEvent(BaseDataEvent event) {
		Map<String,List<BaseData>> cache = (Map<String,List<BaseData>>) CacheHolder.get(BaseDataService.CACHE_CODE_BASE_DATA);
		if(cache == null){
			cache = new HashMap<>();
		}
		if(event.getEventType() != EventType.LIST){
			String dataID = (String) event.getData();
			BaseData data = baseDataService.getData(dataID);
			String categoryCode = data.getDataCategory().getCategoryCode();
			cache.remove(categoryCode);
		}else{
			List<BaseData> listData = (List<BaseData>) event.getSource();
			if(listData.size() > 0){
				String categoryCode = listData.get(0).getDataCategory().getCategoryCode();
				cache.put(categoryCode, listData);
			}
		}
		
		CacheHolder.put(BaseDataService.CACHE_CODE_BASE_DATA, cache);
	}

}
