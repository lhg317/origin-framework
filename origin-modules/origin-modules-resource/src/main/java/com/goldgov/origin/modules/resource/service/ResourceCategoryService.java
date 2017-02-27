package com.goldgov.origin.modules.resource.service;

import java.util.List;

public interface ResourceCategoryService {

	public String addCategory(ResourceCategory category);
	
	public void updateCategory(ResourceCategory category);
	
	public void deleteCategoryByIds(String[] ids);
	
	public ResourceCategory findCategory(String id);
	
	public List<ResourceCategory> findResourceList(ResourceCategoryQuery query);
}
