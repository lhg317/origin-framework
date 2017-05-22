package com.goldgov.origin.modules.node.service;

public class Node {

	public static final String NODE_SESSION_KEY = "$NODE_SESSION_KEY$"; 
	
	private String nodeID;
	private String nodeName;
	private Integer dataNum;//用于拼dataPath的数据序号
	private String dataPath;
	private String adminID;//管理员ID，一般为登录帐号。创建一个节点时必须创建对应的管理员
	private String description;
	
	public String getNodeID() {
		return nodeID;
	}
	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public Integer getDataNum() {
		return dataNum;
	}
	public void setDataNum(Integer dataNum) {
		this.dataNum = dataNum;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getAdminID() {
		return adminID;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
