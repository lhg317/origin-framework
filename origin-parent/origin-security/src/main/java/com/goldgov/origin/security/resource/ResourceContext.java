package com.goldgov.origin.security.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goldgov.origin.core.cache.CacheHolder;

public abstract class ResourceContext {

	private ResourceContext(){}
	
	public static void addResource(Resource resource){
		List<Resource> allResource = getAllResources();
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
		CacheHolder.put(ResourceConstants.CACHE_CODE_PROTECTED_RESOURCE, allResource);
	}
	
	public static Resource getResource(String code){
		List<Resource> allResource = getAllResources();
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
		
		Map<String, String> allResourceMap = getAllResourcesMap();
		allResourceMap.put(path, code);
		CacheHolder.put(ResourceConstants.CACHE_CODE_PROTECTED_RESOURCE_MAPPING, allResourceMap);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Resource> getAllResources(){
		if(CacheHolder.exist(ResourceConstants.CACHE_CODE_PROTECTED_RESOURCE)){
			return (List<Resource>) CacheHolder.get(ResourceConstants.CACHE_CODE_PROTECTED_RESOURCE);
		}else{
			List<Resource> resourceList = new ArrayList<Resource>();
			CacheHolder.put(ResourceConstants.CACHE_CODE_PROTECTED_RESOURCE, resourceList);
			return resourceList;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> getAllResourcesMap(){
		if(CacheHolder.exist(ResourceConstants.CACHE_CODE_PROTECTED_RESOURCE_MAPPING)){
			return (Map<String,String>) CacheHolder.get(ResourceConstants.CACHE_CODE_PROTECTED_RESOURCE_MAPPING);
		}else{
			Map<String,String> resourceMap = new HashMap<>();
			CacheHolder.put(ResourceConstants.CACHE_CODE_PROTECTED_RESOURCE_MAPPING, resourceMap);
			return resourceMap;
		}
	}
}
