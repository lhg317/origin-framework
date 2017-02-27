package com.goldgov.origin.modules.resource.service;

public class ResourceOperate {

	private Integer operateID;

	private String operateName;
	private String operateCode;
	private String operateType;
	private String method;//操作方式，拼接URL或参数方式
	private Resource resource;
	public Integer getOperateID() {
		return operateID;
	}
	public void setOperateID(Integer operateID) {
		this.operateID = operateID;
	}
	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	public String getOperateCode() {
		return operateCode;
	}
	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
}
