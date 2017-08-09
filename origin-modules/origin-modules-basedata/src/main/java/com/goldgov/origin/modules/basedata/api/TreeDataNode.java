package com.goldgov.origin.modules.basedata.api;

import java.util.ArrayList;
import java.util.List;

public class TreeDataNode {

	private final RpcBaseData baseData;
	
	private final List<TreeDataNode> subDataList = new ArrayList<>();

	private final TreeDataNode parentTreeData;

	public TreeDataNode(RpcBaseData baseData,TreeDataNode parentTreeData){
		this.baseData = baseData;
		this.parentTreeData = parentTreeData;
	}
	
	public void addSubData(TreeDataNode baseData){
		subDataList.add(baseData);
	}
	
	public void addAllSubData(List<TreeDataNode> subDataList){
		this.subDataList.addAll(subDataList);
	}

	public List<TreeDataNode> getSubDataList() {
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

	public TreeDataNode getParentTreeData() {
		return parentTreeData;
	}
	
}
