package com.goldgov.origin.modules.resource.service;

public class Resource {

	private Integer resourceID;

	private String resourceName;
	private String resourceCode;
	
	private ResourceCategory resourceCategory;
	
	private ResourceOperate[] resourceOperateList;

	public Integer getResourceID() {
		return resourceID;
	}

	public void setResourceID(Integer resourceID) {
		this.resourceID = resourceID;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}


	public ResourceCategory getResourceCategory() {
		return resourceCategory;
	}

	public void setResourceCategory(ResourceCategory resourceCategory) {
		this.resourceCategory = resourceCategory;
	}

	public ResourceOperate[] getResourceOperateList() {
		return resourceOperateList;
	}

	public void setResourceOperateList(ResourceOperate[] resourceOperateList) {
		this.resourceOperateList = resourceOperateList;
	}

}
