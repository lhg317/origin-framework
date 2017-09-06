package com.goldgov.origin.modules.role.service;

import com.goldgov.origin.core.discovery.rpc.ProxyObject;
import com.goldgov.origin.modules.role.api.RpcRoleObject;

public class ProxyRoleObject extends RoleObject implements ProxyObject<RpcRoleObject>{

	private RpcRoleObject rpcRoleObject;

	public ProxyRoleObject(){
		rpcRoleObject = new RpcRoleObject();
	}

	public ProxyRoleObject(RpcRoleObject rpcRoleObject){
		this.rpcRoleObject = rpcRoleObject;
	}
	
	public ProxyRoleObject(RoleObject roleResource){
		rpcRoleObject = new RpcRoleObject();
		setRoleObjectID(roleResource.getRoleObjectID());
		setRoleID(roleResource.getRoleID());
		setRoleObject(roleResource.getRoleObject());
		setType(roleResource.getType());
	}
	
	public String getRoleObjectID() {
		if(rpcRoleObject.isSetRoleObjectID()){
			return rpcRoleObject.getRoleObjectID();
		}
		return null;
	}
	public void setRoleObjectID(String roleObjectID) {
		if(roleObjectID != null){
			rpcRoleObject.setRoleObjectID(roleObjectID);
		}
	}
	public String getRoleID() {
		if(rpcRoleObject.isSetRoleID()){
			return rpcRoleObject.getRoleID();
		}
		return null;
	}
	public void setRoleID(String roleID) {
		if(roleID != null){
			rpcRoleObject.setRoleID(roleID);
		}
	}
	public String getRoleObject() {
		if(rpcRoleObject.isSetRoleObject()){
			return rpcRoleObject.getRoleObject();
		}
		return null;
	}
	public void setRoleObject(String roleObject) {
		if(roleObject != null){
			rpcRoleObject.setRoleObject(roleObject);
		}
	}
	public String getType() {
		if(rpcRoleObject.isSetType()){
			return rpcRoleObject.getType();
		}
		return null;
	}
	public void setType(String type) {
		if(type != null){
			rpcRoleObject.setType(type);
		}
	}
	
	@Override
	public RpcRoleObject toRpcObject() {
		return rpcRoleObject;
	}
}
