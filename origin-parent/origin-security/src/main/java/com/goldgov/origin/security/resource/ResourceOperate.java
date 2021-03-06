package com.goldgov.origin.security.resource;

import java.io.Serializable;

import com.goldgov.origin.core.web.annotation.OperateType;

public class ResourceOperate implements Serializable{

	private static final long serialVersionUID = -1424245690433943626L;

	private Integer operateID;

	private String operateName;
	private String operateCode;
	private OperateType operateType;
	
	private Integer resourceID;

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
	public OperateType getOperateType() {
		return operateType;
	}
	public void setOperateType(OperateType operateType) {
		this.operateType = operateType;
	}
	public Integer getResourceID() {
		return resourceID;
	}
	public void setResourceID(Integer resourceID) {
		this.resourceID = resourceID;
	}
	
}
