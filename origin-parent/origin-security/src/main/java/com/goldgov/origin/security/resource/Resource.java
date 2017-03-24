package com.goldgov.origin.security.resource;

import java.io.Serializable;
import java.util.List;

public class Resource implements Serializable{

	private static final long serialVersionUID = -2919977030445383820L;

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
