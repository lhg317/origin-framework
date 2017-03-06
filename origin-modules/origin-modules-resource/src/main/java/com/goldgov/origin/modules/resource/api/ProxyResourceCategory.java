package com.goldgov.origin.modules.resource.api;

import com.goldgov.origin.modules.resource.service.ResourceCategory;

public class ProxyResourceCategory extends ResourceCategory{

	private RpcResourceCategory resource;
	
	public ProxyResourceCategory(){
		resource = new RpcResourceCategory();
	}
	
	public ProxyResourceCategory(RpcResourceCategory resource){
		this.resource = resource;
	}
	
	public ProxyResourceCategory(ResourceCategory _resource){
		this();
		if(_resource == null){return;}
		setCategoryID(_resource.getCategoryID());
		setCategoryName(_resource.getCategoryName());
		setDataPath(_resource.getDataPath());
		setDescription(_resource.getDescription());
		setOrderNum(_resource.getOrderNum());
		setParentID(_resource.getParentID());
	}
	
	public Integer getCategoryID() {
		return resource.getCategoryID();
	}
	public void setCategoryID(Integer categoryID) {
		resource.setCategoryID(categoryID);
	}
	public String getCategoryName() {
		return resource.getCategoryName();
	}
	public void setCategoryName(String categoryName) {
		resource.setCategoryName(categoryName);
	}
	public String getDescription() {
		return resource.getDescription();
	}
	public void setDescription(String description) {
		resource.setDescription(description);
	}
	public String getDataPath() {
		return resource.getDataPath();
	}
	public void setDataPath(String dataPath) {
		resource.setDataPath(dataPath);
	}
	public Integer getOrderNum() {
		return resource.getOrderNum();
	}
	public void setOrderNum(Integer orderNum) {
		resource.setOrderNum(orderNum);
	}
	public Integer getParentID() {
		return resource.getParentID();
	}
	public void setParentID(Integer parentID) {
		resource.setParentID(parentID);
	}
	
	public RpcResourceCategory toRpcResourceCategory(){
		return resource;
	}
	
}
