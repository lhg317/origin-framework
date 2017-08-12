package com.goldgov.origin.modules.basedata.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 根据指定的语言环境及分类编码，将其下的所有基础数据值包装一个树形结构的对象
 * @author LiuHG
 * @version 1.0
 */
public class RpcTreeData {

	private final Map<RpcTreeDataNode,List<RpcTreeDataNode>> subDataMap = new HashMap<>();
	
	private final List<RpcTreeDataNode> rootDataList = new ArrayList<>();
	private final List<RpcTreeDataNode> endPointDataList = new ArrayList<>();
	private final List<RpcBaseData> baseDataList;
	private final String localeCode;

	private final String categoryCode;
	
	public RpcTreeData(String localeCode,String categoryCode, List<RpcBaseData> baseDataList){
		this.localeCode = localeCode;
		this.categoryCode = categoryCode;
		this.baseDataList = baseDataList;
		treeFormat();
	}
	
	private void treeFormat(){
		Map<String,List<RpcBaseData>> tempMap = new HashMap<>();
		for (int i = 0; i < baseDataList.size(); i++) {
			RpcBaseData baseData = baseDataList.get(i);
			String parentID = baseData.getParentData().getDataID();
			
			if(parentID.equals(baseData.getDataID())){
				rootDataList.add(new RpcTreeDataNode(baseData,null));
			}else{
				List<RpcBaseData> dataList = tempMap.get(parentID);
				if(dataList == null){
					dataList = new ArrayList<>();
					tempMap.put(parentID, dataList);
				}
				dataList.add(baseData);
			}
		}
		for (int i = 0; i < rootDataList.size(); i++) {
			RpcTreeDataNode treeData = rootDataList.get(i);
			treeFormat(tempMap, treeData);
		}
	}

	private void treeFormat(Map<String, List<RpcBaseData>> tempMap, RpcTreeDataNode treeData) {
		List<RpcBaseData> subDataList = tempMap.remove(treeData.getDataID());
		
		if(subDataList != null){
			for (int i = 0; i < subDataList.size(); i++) {
				RpcTreeDataNode treeDataNode = new RpcTreeDataNode(subDataList.get(i),treeData);
				treeData.addSubData(treeDataNode);
				treeFormat(tempMap,treeDataNode);
			}
		}
		
		subDataMap.put(treeData, treeData.getSubDataList());
		if(treeData.getSubDataList() == null || treeData.getSubDataList().size() < 0){
			endPointDataList.add(treeData);
		}
	}
	
	/**
	 * 根据指定的dataName获取树节点对象，对象中包含所有子集数据
	 * @param dataName 数据名
	 * @return dataName为数据名的节点数据，如果不存在则返回null
	 */
	public RpcTreeDataNode getTreeData(String dataName){
		Iterator<RpcTreeDataNode> keyIterator = subDataMap.keySet().iterator();
		while (keyIterator.hasNext()) {
			RpcTreeDataNode treeData = keyIterator.next();
			if(treeData.getDataName().equals(dataName)){
				return treeData;
			}
			
		}
		return null;
	}
	
	/**
	 * 根据指定的dataName获取其子级数据
	 * @param dataName 数据名
	 * @return dataName为数据名下的所有子级数据，如没有找到，则返回空的集合。
	 */
	public List<RpcTreeDataNode> getTreeDataList(String dataName){
		RpcTreeDataNode treeData = getTreeData(dataName);
		if(treeData != null){
			return subDataMap.get(treeData);
		}
		return Collections.emptyList();
	}

	/**
	 * 以列表形式返回树节点数据。
	 * @return
	 */
	public List<RpcTreeDataNode> getRootDataList() {
		return rootDataList;
	}

	/**
	 * 根据指定的dataName获取其父节点数据对象
	 * @param dataName 数据名
	 * @return dataName为数据名的父节点对象
	 */
	public RpcTreeDataNode getParentTreeData(String dataName) {
		RpcTreeDataNode treeData = getTreeData(dataName);
		if(treeData != null){
			return treeData.getParentTreeData();
		}
		return null;
	}
	
	/**
	 * 根据指定的dataName获取完整的值路径
	 * @param dataName 数据名
	 * @param separator 路径分隔符
	 * @return 指定分隔符分隔的数据值路径，返回的路径以separator开头，但是不以其结尾
	 */
	public String getTreePath(String dataName,String separator) {
		RpcTreeDataNode treeData = getTreeData(dataName);
		StringBuilder sb = new StringBuilder();
		while(treeData != null){
			sb.insert(0,treeData.getDataValue());
			sb.insert(0, separator);
			treeData = treeData.getParentTreeData();
		}
		return sb.toString();
	}

	/**
	 * 获取所有端点数据，即叶节点数据
	 * @return 端点数据
	 */
	public List<RpcTreeDataNode> getEndPointDataList() {
		return endPointDataList;
	}
	
	/**
	 * 返回当前字典分类编码
	 * @return
	 */
	public String getCategoryCode() {
		return categoryCode;
	}

	/**
	 * 返回当前语言标识
	 * @return
	 */
	public String getLocaleCode() {
		return localeCode;
	}
	
}
