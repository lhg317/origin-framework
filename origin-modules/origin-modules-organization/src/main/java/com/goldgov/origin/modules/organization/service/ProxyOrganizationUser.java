package com.goldgov.origin.modules.organization.service;

import com.goldgov.origin.core.discovery.rpc.ProxyObject;
import com.goldgov.origin.modules.organization.api.RpcOrganizationUser;

public class ProxyOrganizationUser extends OrganizationUser implements ProxyObject<RpcOrganizationUser>{

	private RpcOrganizationUser orgUser;
	
	public ProxyOrganizationUser(){
		orgUser = new RpcOrganizationUser();
	}
	
	public ProxyOrganizationUser(RpcOrganizationUser orgUser){
		this.orgUser = orgUser;
	}
	
	public ProxyOrganizationUser(OrganizationUser user){
		orgUser = new RpcOrganizationUser();
		setOrgUserID(user.getOrgUserID());
		setOrgUser(user.getOrgUser());
		setOrganization(user.getOrganization());
	}
	
	public String getOrgUserID() {
		if(orgUser.isSetOrgUserID()){
			return orgUser.getOrgUserID();
		}else{
			return null;
		}
	}
	public void setOrgUserID(String orgUserID) {
		if(orgUserID != null){
			orgUser.setOrgUserID(orgUserID);
		}
	}
	public String getOrgUser() {
		if(orgUser.isSetOrgUser()){
			return orgUser.getOrgUser();
		}else{
			return null;
		}
	}
	public void setOrgUser(String orgUser) {
		if(this.orgUser != null){
			this.orgUser.setOrgUser(orgUser);
		}
	}
	public Organization getOrganization() {
		if(orgUser.isSetOrganization()){
			return new ProxyOrganization(orgUser.getOrganization());
		}else{
			return null;
		}
	}
	public void setOrganization(Organization organization) {
		if(organization != null){
			orgUser.setOrganization(new ProxyOrganization(organization).toRpcObject());
		}
	}

	@Override
	public RpcOrganizationUser toRpcObject() {
		return orgUser;
	}
}
