package com.goldgov.origin.modules.basedata.service;

public class BaseData {

	private String dataID;
	private String dataName;
	private String dataValue;
	private String description;
	private Integer orderNum;
	private Integer nodeValue;
	private String nodePath;
	
	private BaseData parentData;
	
	private BaseDataLocale dataLocale;
	
	public Integer getNodeValue() {
		return nodeValue;
	}
	public void setNodeValue(Integer nodeValue) {
		this.nodeValue = nodeValue;
	}
	public String getNodePath() {
		return nodePath;
	}
	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}
	public BaseData getParentData() {
		return parentData;
	}
	public void setParentData(BaseData parentData) {
		this.parentData = parentData;
	}
	public String getDataID() {
		return dataID;
	}
	public void setDataID(String dataID) {
		this.dataID = dataID;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getDataValue() {
		return dataValue;
	}
	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public BaseDataLocale getDataLocale() {
		return dataLocale;
	}
	public void setDataLocale(BaseDataLocale dataLocale) {
		this.dataLocale = dataLocale;
	}
}
