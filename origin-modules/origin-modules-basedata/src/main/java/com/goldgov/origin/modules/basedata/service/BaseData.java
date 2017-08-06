package com.goldgov.origin.modules.basedata.service;

public class BaseData {

	private String dataID;
	private String dataName;
	private String dataValue;
	private String description;
	private Integer orderNum;
	
	private BaseDataLocale dataLocale;
	
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
