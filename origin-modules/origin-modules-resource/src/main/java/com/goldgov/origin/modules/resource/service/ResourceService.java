package com.goldgov.origin.modules.resource.service;

import java.util.List;
import java.util.Map;

public interface ResourceService {

	public void addCategory(ResourceCategory category);
	
	public void updateCategory(ResourceCategory category);
	
	public void deleteCategory(Integer[] ids);
	
	public ResourceCategory findCategory(Integer id);
	
	public List<ResourceCategory> findResourceCategoryList(ResourceCategoryQuery query);
	
	public void addResource(Resource resource);
	
	public void addOperate(ResourceOperate operate);
	
	public void updateResource(Resource user);
	
	public void updateOperate(ResourceOperate operate);
	
	public void deleteResource(Integer[] ids);
	
	public Resource findResource(Integer id);
	
	public ResourceOperate findOperate(Integer id);
	
	public List<Resource> findResourceList(ResourceQuery query);
	
	public List<Resource> findAllResourceList();
	
	public List<ResourceOperate> findOperateList(ResourceQuery query);
	
	public Map<String,String> findAllResourceMap();
}
