package com.goldgov.origin.modules.organization.service;

import com.goldgov.origin.core.discovery.rpc.ProxyObject;
import com.goldgov.origin.modules.organization.api.RpcOrganizationPost;

public class ProxyOrganizationPost extends OrganizationPost implements ProxyObject<RpcOrganizationPost>{

	private RpcOrganizationPost rpcObj;
	
	public ProxyOrganizationPost(){
		rpcObj = new RpcOrganizationPost();
	}
	
	public ProxyOrganizationPost(RpcOrganizationPost rpcObj){
		this.rpcObj = rpcObj;
	}

	public ProxyOrganizationPost(OrganizationPost obj){
		rpcObj = new RpcOrganizationPost();
		setOrgPostID(obj.getOrgPostID());
		setPostName(obj.getPostName());
		setPostCode(obj.getPostCode());
		setOrganization(obj.getOrganization());
	}
	
	public String getOrgPostID() {
		if(rpcObj.isSetOrgPostID()){
			return rpcObj.getOrgPostID();
		}else{
			return null;
		}
	}
	public void setOrgPostID(String orgPostID) {
		if(orgPostID != null){
			rpcObj.setOrgPostID(orgPostID);
		}
	}
	public String getPostName() {
		if(rpcObj.isSetPostName()){
			return rpcObj.getPostName();
		}else{
			return null;
		}
	}
	public void setPostName(String postName) {
		if(postName != null){
			rpcObj.setPostName(postName);
		}
	}
	public String getPostCode() {
		if(rpcObj.isSetPostCode()){
			return rpcObj.getPostCode();
		}else{
			return null;
		}
	}
	public void setPostCode(String postCode) {
		if(postCode != null){
			rpcObj.setPostCode(postCode);
		}
	}
	public Organization getOrganization() {
		if(rpcObj.isSetOrganization()){
			return new ProxyOrganization(rpcObj.getOrganization());
		}else{
			return null;
		}
	}
	public void setOrganization(Organization organization) {
		if(organization != null){
			rpcObj.setOrganization(new ProxyOrganization(organization).toRpcObject());
		}
	}

	@Override
	public RpcOrganizationPost toRpcObject() {
		return rpcObj;
	}
}
