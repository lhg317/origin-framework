package com.goldgov.origin.modules.role.service;

import com.goldgov.origin.core.discovery.rpc.ProxyObject;
import com.goldgov.origin.modules.role.api.RpcRoleResource;

public class ProxyRoleResource extends RoleResource implements ProxyObject<RpcRoleResource>{

	private RpcRoleResource rpcRoleResource;
	
	public ProxyRoleResource(){
		rpcRoleResource = new RpcRoleResource();
	}

	public ProxyRoleResource(RpcRoleResource rpcRoleResource){
		this.rpcRoleResource = rpcRoleResource;
	}
	
	public ProxyRoleResource(RoleResource roleResource){
		rpcRoleResource = new RpcRoleResource();
		setRoleResourceID(roleResource.getRoleResourceID());
		setRoleID(roleResource.getRoleID());
		setResourceOperate(roleResource.getResourceOperate());
	}
	
	public String getRoleResourceID() {
		if(rpcRoleResource.isSetRoleResourceID()){
			return rpcRoleResource.getRoleResourceID();
		}else{
			return null;
		}
	}
	public void setRoleResourceID(String roleResourceID) {
		if(roleResourceID != null){
			rpcRoleResource.setRoleResourceID(roleResourceID);
		}
	}
	
	public String getRoleID() {
		if(rpcRoleResource.isSetRoleID()){
			return rpcRoleResource.getRoleID();
		}else{
			return null;
		}
	}
	public void setRoleID(String roleID) {
		if(roleID != null){
			rpcRoleResource.setRoleID(roleID);
		}
	}
	public String getResourceOperate() {
		if(rpcRoleResource.isSetResourceOperate()){
			return rpcRoleResource.getResourceOperate();
		}else{
			return null;
		}
	}
	public void setResourceOperate(String resourceOperate) {
		if(resourceOperate != null){
			rpcRoleResource.setResourceOperate(resourceOperate);
		}
	}

	@Override
	public RpcRoleResource toRpcObject() {
		return rpcRoleResource;
	}
}
