package com.goldgov.origin.modules.resource.dao;

import java.util.List;

import com.goldgov.origin.modules.resource.service.Resource;
import com.goldgov.origin.modules.resource.service.ResourceOperate;
import com.goldgov.origin.modules.resource.service.ResourceQuery;

public interface ResourceDao {

	public void addResource(Resource resource);
	
	public void addOperate(ResourceOperate operate);
	
	public void updateResource(Resource user);
	
	public void updateOperate(ResourceOperate operate);
	
	public void deleteResourceByIds(String[] ids);
	
	public void deleteOperateByIds(String[] ids);
	
	public Resource findResourceById(String id);
	
	public ResourceOperate findOperateById(String id);
	
	public Resource findResourceByLoginName(String loginName);
	
	public List<Resource> findResourceList(ResourceQuery query);
	
	public List<ResourceOperate> findOperateList(ResourceQuery query);
	
	public boolean resourceExist(String code);
}
