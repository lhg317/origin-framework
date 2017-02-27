package com.goldgov.origin.modules.resource.service;

public class Resource {

	private Integer resourceID;

	private String resourceName;
	private String resourceCode;
	private String parameterName;
	private String resourceUri;
	private String method;
	
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

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getResourceUri() {
		return resourceUri;
	}

	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
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
