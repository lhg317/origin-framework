package com.goldgov.origin.modules.role.service;

import java.util.List;

public class Resource {

	private Integer resourceID;
	private String resourceName;
	private String resourceCode;
	
	private List<ResourceOperate> resourceOperateList;

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

	public List<ResourceOperate> getResourceOperateList() {
		return resourceOperateList;
	}

	public void setResourceOperateList(List<ResourceOperate> resourceOperateList) {
		this.resourceOperateList = resourceOperateList;
	}

}
