package com.goldgov.origin.modules.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goldgov.origin.core.Keys;
import com.goldgov.origin.core.cache.CacheHolder;
import com.goldgov.origin.modules.resource.service.Resource;

public abstract class ResourceContext {

	private ResourceContext(){}
	
	public static void addResource(Resource resource){
		List<Resource> allResource = getAllResource();
		int existIndex = -1;
		for (int i = 0;i < allResource.size() ; i++) {
			Resource _resource = allResource.get(i);
			if(_resource.getResourceCode().equals(resource.getResourceCode())){
				existIndex = i;
				break;
			}
		}
		
		if(existIndex != -1){
			allResource.set(existIndex, resource);
		}else{
			allResource.add(resource);
		}
		CacheHolder.put(Keys.CACHE_CODE_PROTECTED_RESOURCE, allResource);
	}
	
	public static Resource getResource(String code){
		List<Resource> allResource = getAllResource();
		for (int i = 0;i < allResource.size() ; i++) {
			Resource _resource = allResource.get(i);
			if(_resource.getResourceCode().equals(code)){
				return _resource;
			}
		}
		
		return null;
	}
	
	public static void addPathResource(String path,String code){
		path = path.startsWith("/")? path : "/" + path;
		
		Map<String, String> allResourceMap = getAllResourceMap();
		allResourceMap.put(path, code);
		CacheHolder.put(Keys.CACHE_CODE_PROTECTED_RESOURCE_MAPPING, allResourceMap);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Resource> getAllResource(){
		if(CacheHolder.exist(Keys.CACHE_CODE_PROTECTED_RESOURCE)){
			return (List<Resource>) CacheHolder.get(Keys.CACHE_CODE_PROTECTED_RESOURCE);
		}else{
			List<Resource> resourceList = new ArrayList<Resource>();
			CacheHolder.put(Keys.CACHE_CODE_PROTECTED_RESOURCE, resourceList);
			return resourceList;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> getAllResourceMap(){
		if(CacheHolder.exist(Keys.CACHE_CODE_PROTECTED_RESOURCE_MAPPING)){
			return (Map<String,String>) CacheHolder.get(Keys.CACHE_CODE_PROTECTED_RESOURCE_MAPPING);
		}else{
			Map<String,String> resourceMap = new HashMap<>();
			CacheHolder.put(Keys.CACHE_CODE_PROTECTED_RESOURCE_MAPPING, resourceMap);
			return resourceMap;
		}
	}
}
