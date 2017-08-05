package com.goldgov.origin.modules.basedata.service;

import com.goldgov.origin.core.discovery.rpc.ProxyObject;
import com.goldgov.origin.modules.basedata.api.RpcBaseData;

public class ProxyBaseData extends BaseData implements ProxyObject<RpcBaseData>{

	private RpcBaseData rpcBaseData;
	
	public ProxyBaseData(){
		rpcBaseData = new RpcBaseData();
	}
	
	public ProxyBaseData(RpcBaseData rpcBaseData){
		this.rpcBaseData = rpcBaseData;
	}
	
	public ProxyBaseData(BaseData baseData){
		rpcBaseData = new RpcBaseData();
		rpcBaseData.setDataID(baseData.getDataID());
		rpcBaseData.setDataName(baseData.getDataName());
		rpcBaseData.setDataValue(baseData.getDataValue());
		if(baseData.getDataLocale() != null){
			ProxyBaseDataLocale locale = new ProxyBaseDataLocale(baseData.getDataLocale());
			rpcBaseData.setDataLocale(locale.toRpcObject());
		}
	}
	
	public String getDataID() {
		return rpcBaseData.getDataID();
	}
	public void setDataID(String dataID) {
		rpcBaseData.setDataID(dataID);
	}
	public String getDataName() {
		if(rpcBaseData.isSetDataName()){
			return rpcBaseData.getDataName();
		}else{
			return null;
		}
	}
	public void setDataName(String dataName) {
		rpcBaseData.setDataName(dataName);
	}
	public String getDataValue() {
		if(rpcBaseData.isSetDataValue()){
			return rpcBaseData.getDataValue();
		}else{
			return null;
		}
	}
	public void setDataValue(String dataValue) {
		rpcBaseData.setDataValue(dataValue);
	}
	public String getDescription() {
		if(rpcBaseData.isSetDescription()){
			return rpcBaseData.getDescription();
		}else{
			return null;
		}
	}
	public void setDescription(String description) {
		rpcBaseData.setDescription(description);
	}
	public Integer getOrderNum() {
		if(rpcBaseData.isSetOrderNum()){
			return rpcBaseData.getOrderNum();
		}else{
			return null;
		}
	}
	public void setOrderNum(Integer orderNum) {
		rpcBaseData.setOrderNum(orderNum);
	}
	public BaseDataLocale getDataLocale() {
		if(rpcBaseData.getDataLocale() != null){
			return new ProxyBaseDataLocale(rpcBaseData.getDataLocale());
		}
		return null;
	}
	public void setDataLocale(BaseDataLocale dataLocale) {
		if(dataLocale != null){
			rpcBaseData.setDataLocale(new ProxyBaseDataLocale(dataLocale).toRpcObject());
		}
	}

	@Override
	public RpcBaseData toRpcObject() {
		return rpcBaseData;
	}
}
