package com.goldgov.origin.modules.basedata.service;

import com.goldgov.origin.core.discovery.rpc.ProxyObject;
import com.goldgov.origin.modules.basedata.api.RpcBaseDataCategory;

public class ProxyBaseDataCategory extends BaseDataCategory implements ProxyObject<RpcBaseDataCategory>{

	private RpcBaseDataCategory rpcBaseDataCategory;
	
	public ProxyBaseDataCategory(){
		rpcBaseDataCategory = new RpcBaseDataCategory();
	}
	
	public ProxyBaseDataCategory(RpcBaseDataCategory rpcBaseDataCategory){
		this.rpcBaseDataCategory = rpcBaseDataCategory;
	}
	
	public ProxyBaseDataCategory(BaseDataCategory baseDataCategory){
		rpcBaseDataCategory = new RpcBaseDataCategory();
		setCategoryID(baseDataCategory.getCategoryID());
		setCategoryName(baseDataCategory.getCategoryName());
		setCategoryCode(baseDataCategory.getCategoryCode());
	}
	
	public String getCategoryID() {
		if(rpcBaseDataCategory.isSetCategoryID()){
			return rpcBaseDataCategory.getCategoryID();
		}else{
			return null;
		}
	}
	public void setCategoryID(String categoryID) {
		if(categoryID != null){
			rpcBaseDataCategory.setCategoryCode(categoryID);
		}
	}
	public String getCategoryName() {
		if(rpcBaseDataCategory.isSetCategoryName()){
			return rpcBaseDataCategory.getCategoryName();
		}else{
			return null;
		}
	}
	public void setCategoryName(String categoryName) {
		if(categoryName != null){
			rpcBaseDataCategory.setCategoryName(categoryName);
		}
	}
	public String getCategoryCode() {
		if(rpcBaseDataCategory.isSetCategoryCode()){
			return rpcBaseDataCategory.getCategoryCode();
		}else{
			return null;
		}
	}
	public void setCategoryCode(String categoryCode) {
		if(categoryCode != null){
			rpcBaseDataCategory.setCategoryCode(categoryCode);
		}
	}
	
	@Override
	public RpcBaseDataCategory toRpcObject() {
		return rpcBaseDataCategory;
	}

}
