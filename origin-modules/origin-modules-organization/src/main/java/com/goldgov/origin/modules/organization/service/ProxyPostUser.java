package com.goldgov.origin.modules.organization.service;

import com.goldgov.origin.core.discovery.rpc.ProxyObject;
import com.goldgov.origin.modules.organization.api.RpcPostUser;

public class ProxyPostUser extends PostUser implements ProxyObject<RpcPostUser>{

	private RpcPostUser rpcObj;
	
	public ProxyPostUser(){
		rpcObj = new RpcPostUser();
	}
	public ProxyPostUser(RpcPostUser rpcObj){
		this.rpcObj = rpcObj;
	}
	public ProxyPostUser(PostUser obj){
		rpcObj = new RpcPostUser();
		setPostUserID(obj.getPostUserID());
		setOrgPost(obj.getOrgPost());
		setOrgUser(obj.getOrgUser());
	}
	
	public String getPostUserID() {
		if(rpcObj.isSetPostUserID()){
			return rpcObj.getPostUserID();
		}else{
			return null;
		}
	}
	public void setPostUserID(String postUserID) {
		if(postUserID != null){
			rpcObj.setPostUserID(postUserID);
		}
	}
	public OrganizationPost getOrgPost() {
		if(rpcObj.isSetOrgPost()){
			return new ProxyOrganizationPost(rpcObj.getOrgPost());
		}else{
			return null;
		}
	}
	public void setOrgPost(OrganizationPost orgPost) {
		if(orgPost != null){
			rpcObj.setOrgPost(new ProxyOrganizationPost(rpcObj.getOrgPost()).toRpcObject());
		}
	}
	public OrganizationUser getOrgUser() {
		if(rpcObj.isSetOrgUser()){
			return new ProxyOrganizationUser(rpcObj.getOrgUser());
		}else{
			return null;
		}
	}
	public void setOrgUser(OrganizationUser orgUser) {
		if(orgUser != null){
			rpcObj.setOrgUser(new ProxyOrganizationUser(rpcObj.getOrgUser()).toRpcObject());
		}
	}
	

	@Override
	public RpcPostUser toRpcObject() {
		return rpcObj;
	}
}
