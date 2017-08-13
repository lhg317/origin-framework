package com.goldgov.origin.modules.basedata.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiuHG
 * @version 1.0
 */
public class TreeDataNode {

	private final BaseData baseData;
	
	private final List<TreeDataNode> subDataList = new ArrayList<>();

	private final TreeDataNode parentTreeData;
	
	private int endPointNum = 0;

	private int deep;
	
	public TreeDataNode(BaseData baseData,TreeDataNode parentTreeData){
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

	public BaseData getBaseData() {
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
	
	protected void incrementEndPointNum(){
		endPointNum++;
		if(parentTreeData != null){
			parentTreeData.incrementEndPointNum();
		}
	}

	public int getEndPointNum() {
		return endPointNum;
	}
	public int getDeep() {
		return deep;
	}

	void setDeep(int deep) {
		this.deep = deep;
	}
	
	public boolean isEndPoint(){
		return subDataList.size() == 0;
	}

}
