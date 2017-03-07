package com.goldgov.origin.modules.resource.api;

import com.goldgov.origin.core.web.annotation.OperateType;
import com.goldgov.origin.modules.resource.service.ResourceOperate;

public class ProxyResourceOperate extends ResourceOperate{

	private RpcResourceOperate operate;
	
	public ProxyResourceOperate(){
		operate = new RpcResourceOperate();
	}
	
	public ProxyResourceOperate(RpcResourceOperate resource){
		this.operate = resource;
	}
	
	public ProxyResourceOperate(ResourceOperate _operate){
		this();
		if(_operate == null){return;}
		setOperateID(_operate.getOperateID());
		setOperateName(_operate.getOperateName());
		setOperateCode(_operate.getOperateCode());
		setResourceID(_operate.getResourceID());
	}
	
	public Integer getOperateID() {
		return operate.getOperateID();
	}
	public void setOperateID(Integer operateID) {
		if(operateID == null){
			operate.unsetOperateID();
			return;
		}
		operate.setOperateID(operateID);
	}
	public String getOperateName() {
		return operate.getOperateName();
	}
	public void setOperateName(String operateName) {
		operate.setOperateName(operateName);
	}
	public String getOperateCode() {
		return operate.getOperateCode();
	}
	public void setOperateCode(String operateCode) {
		operate.setOperateCode(operateCode);
	}
	public OperateType getOperateType() {
		return Enum.valueOf(OperateType.class, String.valueOf(operate.getOperateType()));
	}
	public void setOperateType(OperateType operateType) {
		operate.setOperateType(Enum.valueOf(RpcOperateType.class, String.valueOf(operateType)));
	}
	public Integer getResourceID() {
		return operate.getResourceID();
	}
	public void setResourceID(Integer resourceID) {
		if(resourceID == null){
			operate.unsetResourceID();
			return;
		}
		operate.setResourceID(resourceID);
	}
	
	public RpcResourceOperate toRpcResourceOperate(){
		return operate;
	}
}
