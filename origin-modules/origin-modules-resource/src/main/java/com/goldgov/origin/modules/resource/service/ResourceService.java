package com.goldgov.origin.modules.resource.service;

import java.util.List;

public interface ResourceService {

	public String addResource(Resource resource);
	
	public String addOperate(ResourceOperate operate);
	
	public void updateResource(Resource user);
	
	public void updateOperate(ResourceOperate operate);
	
	public void deleteResource(Integer[] ids);
	
	public void deleteOperate(Integer[] ids);
	
	public Resource findResource(Integer id);
	
	public ResourceOperate findOperate(Integer id);
	
	public List<Resource> findResourceList(ResourceQuery query);
	
	public List<ResourceOperate> findOperateList(ResourceQuery query);
	
	public boolean resourceExist(String code);
}
