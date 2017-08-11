package com.goldgov.origin.modules.organization.service;

import com.goldgov.origin.core.discovery.rpc.ProxyObject;
import com.goldgov.origin.modules.organization.api.RpcOrganization;

/**
 * 组织机构管理
 * @author LiuHG
 *
 */
public class ProxyOrganization extends Organization implements ProxyObject<RpcOrganization>{

	private RpcOrganization rpcOrganization;
	
	public ProxyOrganization(){
		rpcOrganization = new RpcOrganization();
	}
	
	public ProxyOrganization(RpcOrganization rpcOrganization){
		this.rpcOrganization = rpcOrganization;
	}
	
	public ProxyOrganization(Organization organization){
		rpcOrganization = new RpcOrganization();
		setOrgID(organization.getOrgID());
		setOrgName(organization.getOrgName());
		setOrgCode(organization.getOrgCode());
		setAbbreviation(organization.getAbbreviation());
		setNodePath(organization.getNodePath());
		setNodeValue(organization.getNodeValue());
		
		if(organization.getParentOrganization() != null){
			setParentOrganization(organization);
		}
	}
	
	
	public String getOrgID() {
		if(rpcOrganization.isSetOrgID()){
			return rpcOrganization.getOrgID();
		}else{
			return null;
		}
	}
	public void setOrgID(String orgID) {
		if(orgID != null){
			rpcOrganization.setOrgID(orgID);
		}
	}
	public String getOrgName() {
		if(rpcOrganization.isSetOrgName()){
			return rpcOrganization.getOrgName();
		}else{
			return null;
		}
	}
	public void setOrgName(String orgName) {
		if(orgName != null){
			rpcOrganization.setOrgName(orgName);
		}
	}
	public String getOrgCode() {
		if(rpcOrganization.isSetOrgCode()){
			return rpcOrganization.getOrgCode();
		}else{
			return null;
		}
	}
	public void setOrgCode(String orgCode) {
		if(orgCode != null){
			rpcOrganization.setOrgCode(orgCode);
		}
	}
	public String getAbbreviation() {
		if(rpcOrganization.isSetAbbreviation()){
			return rpcOrganization.getAbbreviation();
		}else{
			return null;
		}
	}
	public void setAbbreviation(String abbreviation) {
		if(abbreviation != null){
			rpcOrganization.setAbbreviation(abbreviation);
		}
	}
	public String getNodePath() {
		if(rpcOrganization.isSetNodePath()){
			return rpcOrganization.getNodePath();
		}else{
			return null;
		}
	}
	public void setNodePath(String nodePath) {
		if(nodePath != null){
			rpcOrganization.setNodePath(nodePath);
		}
	}
	public Integer getNodeValue() {
		if(rpcOrganization.isSetNodeValue()){
			return rpcOrganization.getNodeValue();
		}else{
			return null;
		}
	}
	public void setNodeValue(Integer nodeValue) {
		if(nodeValue != null){
			rpcOrganization.setNodeValue(nodeValue);
		}
	}
	
	public Organization getParentOrganization() {
		if(rpcOrganization.isSetParentOrganization()){
			return new ProxyOrganization(rpcOrganization.getParentOrganization());
		}else{
			return null;
		}
	}
	public void setParentOrganization(Organization parentOrganization) {
		if(parentOrganization != null){
			rpcOrganization.setParentOrganization(new ProxyOrganization(parentOrganization).toRpcObject());
		}
	}
	
	@Override
	public RpcOrganization toRpcObject() {
		return rpcOrganization;
	}
	
	
}
