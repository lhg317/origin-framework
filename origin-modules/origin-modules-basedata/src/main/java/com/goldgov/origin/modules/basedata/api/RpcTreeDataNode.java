package com.goldgov.origin.modules.basedata.api;

import java.util.ArrayList;
import java.util.List;

public class RpcTreeDataNode {

	private final RpcBaseData baseData;
	
	private final List<RpcTreeDataNode> subDataList = new ArrayList<>();

	private final RpcTreeDataNode parentTreeData;

	public RpcTreeDataNode(RpcBaseData baseData,RpcTreeDataNode parentTreeData){
		this.baseData = baseData;
		this.parentTreeData = parentTreeData;
	}
	
	public void addSubData(RpcTreeDataNode baseData){
		subDataList.add(baseData);
	}
	
	public void addAllSubData(List<RpcTreeDataNode> subDataList){
		this.subDataList.addAll(subDataList);
	}

	public List<RpcTreeDataNode> getSubDataList() {
		return subDataList;
	}

	public RpcBaseData getBaseData() {
		return baseData;
	}
	
	public String getDataID() {
		return baseData.getDataID();
	}
	
	public String getDataName() {
		return baseData.getDataName();
	}
	
	public String getDataValue() {
		return baseData.getDataValue();
	}

	public RpcTreeDataNode getParentTreeData() {
		return parentTreeData;
	}
	
}
