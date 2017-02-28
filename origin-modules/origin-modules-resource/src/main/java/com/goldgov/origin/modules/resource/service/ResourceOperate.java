package com.goldgov.origin.modules.resource.service;

import com.goldgov.origin.core.web.annotation.OperateType;

public class ResourceOperate {

	private Integer operateID;

	private String operateName;
	private String operateCode;
	private OperateType operateType;
	
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
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public OperateType getOperateType() {
		return operateType;
	}
	public void setOperateType(OperateType operateType) {
		this.operateType = operateType;
	}
}
