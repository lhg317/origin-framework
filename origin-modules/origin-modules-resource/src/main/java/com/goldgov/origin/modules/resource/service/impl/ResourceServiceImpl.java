package com.goldgov.origin.modules.resource.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldgov.origin.modules.resource.ResourceContext;
import com.goldgov.origin.modules.resource.dao.ResourceDao;
import com.goldgov.origin.modules.resource.service.Resource;
import com.goldgov.origin.modules.resource.service.ResourceCategory;
import com.goldgov.origin.modules.resource.service.ResourceCategoryQuery;
import com.goldgov.origin.modules.resource.service.ResourceOperate;
import com.goldgov.origin.modules.resource.service.ResourceQuery;
import com.goldgov.origin.modules.resource.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public void addCategory(ResourceCategory category) {
		resourceDao.addCategory(category);;
	}

	@Override
	public void updateCategory(ResourceCategory category) {
		resourceDao.updateCategory(category);
	}

	@Override
	public void deleteCategory(Integer[] ids) {
		resourceDao.deleteCategory(ids);
	}

	@Override
	public ResourceCategory findCategory(Integer id) {
		return resourceDao.findCategory(id);
	}

	@Override
	public List<ResourceCategory> findResourceCategoryList(ResourceCategoryQuery query) {
		return resourceDao.findResourceCategoryList(query);
	}

	@Override
	public void addResource(Resource resource) {
		resourceDao.addResource(resource);
	}

	@Override
	public void addOperate(ResourceOperate operate) {
		resourceDao.addOperate(operate);
	}

	@Override
	public void updateResource(Resource resource) {
		resourceDao.updateResource(resource);
	}

	@Override
	public void updateOperate(ResourceOperate operate) {
		resourceDao.updateOperate(operate);
	}

	@Override
	public void deleteResource(Integer[] ids) {
		resourceDao.deleteResource(ids);
	}

	@Override
	public Resource findResource(Integer id) {
		return resourceDao.findResource(id);
	}

	@Override
	public ResourceOperate findOperate(Integer id) {
		return resourceDao.findOperate(id);
	}

	@Override
	public List<Resource> findResourceList(ResourceQuery query) {
		return resourceDao.findResourceList(query);
	}

	@Override
	public List<ResourceOperate> findOperateList(ResourceQuery query) {
		return resourceDao.findOperateList(query);
	}

	@Override
	public List<Resource> findAllResourceList() {
		List<Resource> allResource = ResourceContext.getAllResource();
		return allResource;
	}

	@Override
	public Map<String, String> findAllResourceMap() {
		return ResourceContext.getAllResourceMap();
	}

}
