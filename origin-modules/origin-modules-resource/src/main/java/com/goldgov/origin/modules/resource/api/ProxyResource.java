package com.goldgov.origin.modules.resource.api;

import java.util.ArrayList;
import java.util.List;

import com.goldgov.origin.modules.resource.service.Resource;
import com.goldgov.origin.modules.resource.service.ResourceCategory;
import com.goldgov.origin.modules.resource.service.ResourceOperate;

public class ProxyResource extends Resource{

	private RpcResource resource;
	
	public ProxyResource(){
		resource = new RpcResource();
	}
	
	public ProxyResource(RpcResource resource){
		this.resource = resource;
	}
	
	public ProxyResource(Resource _resource){
		this();
		if(_resource == null){return;}
		setResourceID(_resource.getResourceID());
		setResourceName(_resource.getResourceName());
		setResourceCode(_resource.getResourceCode());
		setResourceCategory(_resource.getResourceCategory());
		setResourceOperateList(_resource.getResourceOperateList());
	}
	
	public Integer getResourceID() {
		return resource.getResourceID();
	}

	public void setResourceID(Integer resourceID) {
		if(resourceID == null){
			resource.unsetResourceID();
			return;
		}
		
		resource.setResourceID(resourceID);
	}

	public String getResourceName() {
		return resource.getResourceName();
	}

	public void setResourceName(String resourceName) {
		resource.setResourceName(resourceName);
	}

	public String getResourceCode() {
		return resource.getResourceCode();
	}

	public void setResourceCode(String resourceCode) {
		resource.setResourceCode(resourceCode);
	}


	public ResourceCategory getResourceCategory() {
		return new ProxyResourceCategory(resource.getResourceCategory());
	}

	public void setResourceCategory(ResourceCategory resourceCategory) {
		resource.setResourceCategory(new ProxyResourceCategory(resourceCategory).toRpcResourceCategory());
	}

	public List<ResourceOperate> getResourceOperateList() {
		List<RpcResourceOperate> rpcOperateList = resource.getResourceOperateList();
		List<ResourceOperate> operateList = new ArrayList<>(rpcOperateList.size());
		for (RpcResourceOperate rpcOperate : rpcOperateList) {
			operateList.add(new ProxyResourceOperate(rpcOperate));
		}
		return operateList;
	}

	public void setResourceOperateList(List<ResourceOperate> resourceOperateList) {
		List<RpcResourceOperate> rpcOperateList = new ArrayList<>(resourceOperateList.size());
		for (ResourceOperate operate : resourceOperateList) {
			rpcOperateList.add(new ProxyResourceOperate(operate).toRpcResourceOperate());
		}
		resource.setResourceOperateList(rpcOperateList);
	}
	
	public RpcResource toRpcResource(){
		return resource;
	}
	
}
