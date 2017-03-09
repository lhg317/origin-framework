package com.goldgov.origin.modules.resource.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldgov.origin.core.discovery.rpc.RpcService;
import com.goldgov.origin.modules.resource.ResourceContext;
import com.goldgov.origin.modules.resource.api.ProxyResource;
import com.goldgov.origin.modules.resource.api.RpcResource;
import com.goldgov.origin.modules.resource.api.RpcResourceCategory;
import com.goldgov.origin.modules.resource.api.RpcResourceCategoryQuery;
import com.goldgov.origin.modules.resource.api.RpcResourceOperate;
import com.goldgov.origin.modules.resource.api.RpcResourceQuery;
import com.goldgov.origin.modules.resource.api.RpcResourceService;
import com.goldgov.origin.modules.resource.service.Resource;
import com.goldgov.origin.modules.resource.service.ResourceService;

@RpcService
public class RpcResourceServiceImpl implements RpcResourceService.Iface{

	@Autowired
	private ResourceService resourceService;

	@Override
	public void addCategory(RpcResourceCategory category) throws TException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCategory(RpcResourceCategory category) throws TException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCategory(List<Integer> ids) throws TException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RpcResourceCategory findCategory(int id) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RpcResourceCategory> findResourceCategoryList(RpcResourceCategoryQuery query) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addResource(RpcResource resource) throws TException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addOperate(RpcResourceOperate operate) throws TException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateResource(RpcResource user) throws TException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOperate(RpcResourceOperate operate) throws TException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteResource(List<Integer> ids) throws TException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RpcResource findResource(int id) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RpcResourceOperate findOperate(int id) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RpcResource> findResourceList(RpcResourceQuery query) throws TException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<RpcResource> getAllResource() throws TException {
//		List<Resource> allResource = ResourceContext.getAllResource();
//		List<RpcResource> resultList = new ArrayList<>();
//		for (Resource resource : allResource) {
//			resultList.add(new ProxyResource(resource).toRpcResource());
//		}
//		return resultList;
//	}

	@Override
	public Map<String, String> getAllResourceMap() throws TException {
		return ResourceContext.getAllResourceMap();
	}

	@Override
	public List<RpcResource> getAllResource() throws TException {
		List<Resource> allResourceList = resourceService.findAllResourceList();
		List<RpcResource> allRpcResourceList = new ArrayList<>(allResourceList.size());
		for (Resource resource : allResourceList) {
			allRpcResourceList.add(new ProxyResource(resource).toRpcResource());
		}
		return allRpcResourceList;
	}

	

}
