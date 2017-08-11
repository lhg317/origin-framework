package com.goldgov.origin.modules.organization.service;


/**
 * 组织机构管理
 * @author LiuHG
 */
public class Organization {
	
	private String orgID;
	private String orgName;
	private String orgCode;
	private String abbreviation;
	private String nodePath;
	private Integer nodeValue;
	
	public String getOrgID() {
		return orgID;
	}
	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getNodePath() {
		return nodePath;
	}
	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}
	public Integer getNodeValue() {
		return nodeValue;
	}
	public void setNodeValue(Integer nodeValue) {
		this.nodeValue = nodeValue;
	}
	
}
