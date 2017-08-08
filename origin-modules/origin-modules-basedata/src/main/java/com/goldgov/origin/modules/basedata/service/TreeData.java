package com.goldgov.origin.modules.basedata.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeData {

	private final List<TreeDataNode> rootDataList = new ArrayList<>();
	private final List<BaseData> baseDataList;
	private final String localeCode;
	
	public TreeData(String localeCode,List<BaseData> baseDataList){
		this.localeCode = localeCode;
		this.baseDataList = baseDataList;
	}
	
	public void treeFormat(){
		Map<String,List<BaseData>> tempMap = new HashMap<>();
		for (int i = 0; i < baseDataList.size(); i++) {
			BaseData baseData = baseDataList.get(i);
			String parentID = baseData.getParentData().getDataID();
			
			if(parentID.equals(baseData.getDataID())){
				rootDataList.add(new TreeDataNode(baseData));
			}else{
				List<BaseData> dataList = tempMap.get(parentID);
				if(dataList == null){
					dataList = new ArrayList<>();
					tempMap.put(parentID, dataList);
				}
				dataList.add(baseData);
			}
		}
		for (int i = 0; i < rootDataList.size(); i++) {
			TreeDataNode treeData = rootDataList.get(i);
			treeFormat(tempMap, treeData);
		}
	}

	private void treeFormat(Map<String, List<BaseData>> tempMap, TreeDataNode treeData) {
		List<BaseData> subDataList = tempMap.remove(treeData.getDataID());
		if(subDataList != null){
			treeData.addAllSubData(subDataList);
			for (int j = 0; j < subDataList.size(); j++) {
				BaseData baseData = subDataList.get(j);
				treeFormat(tempMap,new TreeDataNode(baseData));
			}
		}
	}

	public List<TreeDataNode> getRootDataList() {
		return rootDataList;
	}

	public String getLocaleCode() {
		return localeCode;
	}
	
}
