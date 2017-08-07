package com.goldgov.origin.modules.basedata.service;

import java.util.ArrayList;
import java.util.List;

public class TreeDataNode {

	private final BaseData baseData;
	
	private List<BaseData> subDataList = new ArrayList<>();

	public TreeDataNode(BaseData baseData){
		this.baseData = baseData;
	}
	
	public void addSubData(BaseData baseData){
		subDataList.add(baseData);
	}
	
	public void addAllSubData(List<BaseData> subDataList){
		subDataList.addAll(subDataList);
	}

	public List<BaseData> getSubDataList() {
		return subDataList;
	}

	public BaseData getBaseData() {
		return baseData;
	}
	
	public String getDataID() {
		return baseData.getDataID();
	}
	
}
