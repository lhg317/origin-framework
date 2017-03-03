package com.goldgov.origin.modules.resource.dao;

import java.util.List;

import com.goldgov.origin.modules.resource.service.Resource;
import com.goldgov.origin.modules.resource.service.ResourceCategory;
import com.goldgov.origin.modules.resource.service.ResourceCategoryQuery;
import com.goldgov.origin.modules.resource.service.ResourceOperate;
import com.goldgov.origin.modules.resource.service.ResourceQuery;

public interface ResourceDao {

	public void addCategory(ResourceCategory category);

	public void updateCategory(ResourceCategory category);
	
	public void deleteCategory(Integer[] ids);
	
	public void addResource(Resource resource);
	
	public void addOperate(ResourceOperate operate);
	
	public void updateResource(Resource resource);
	
	public void updateOperate(ResourceOperate operate);
	
	public void deleteResource(Integer[] ids);
	
	public void deleteOperateByIds(Integer[] ids);
	
	public Resource findResource(Integer id);
	
	public ResourceCategory findCategory(Integer id);
	
	public ResourceOperate findOperate(Integer id);
	
	public List<ResourceCategory> findResourceCategoryList(ResourceCategoryQuery query);
	
	public List<Resource> findResourceList(ResourceQuery query);
	
	public List<ResourceOperate> findOperateList(ResourceQuery query);
	
	public boolean resourceExist(String code);
}
