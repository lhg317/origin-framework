package com.goldgov.origin.modules.resource.api;

import java.util.ArrayList;
import java.util.List;

import com.goldgov.origin.modules.resource.service.ResourceCategory;
import com.goldgov.origin.modules.resource.service.ResourceCategoryQuery;

public class ProxyResourceCategoryQuery extends ResourceCategoryQuery {

	private RpcResourceCategoryQuery roleQuery;
	
	public ProxyResourceCategoryQuery(){
		roleQuery = new RpcResourceCategoryQuery();
	}
	
	public ProxyResourceCategoryQuery(RpcResourceCategoryQuery query){
		this.roleQuery = query;
	}

	@Override
	public int getPageSize() {
		return roleQuery.getPageSize();
	}

	@Override
	public void setPageSize(int pageSize) {
		roleQuery.setPageSize(pageSize);
	}

	@Override
	public int getCurrentPage() {
		return roleQuery.getCurrentPage();
	}

	@Override
	public void setCurrentPage(int currentPage) {
		roleQuery.setCurrentPage(currentPage);
	}

	@Override
	public long getCount() {
		return roleQuery.getCount();
	}

	@Override
	public void setCount(long count) {
		roleQuery.setCount(count);
	}

	@Override
	public int getMaxPage() {
		return roleQuery.getMaxPage();
	}

	@Override
	public void setMaxPage(int maxPage) {
		roleQuery.setMaxPage(maxPage);
	}

	@Override
	public void setFirstResult(int firstResult) {
		roleQuery.setFirstResult(firstResult);
	}

	@Override
	public int getFirstResult() {
		return roleQuery.getFirstResult();
	}

	@Override
	public int getMinPage() {
		return roleQuery.getMinPage();
	}

	@Override
	public void setMinPage(int minPage) {
		roleQuery.setMinPage(minPage);
	}

	@Override
	public List<ResourceCategory> getResultList() {
		List<RpcResourceCategory> rpcRoleList = roleQuery.getResultList();
		List<ResourceCategory> resultList = new ArrayList<>(rpcRoleList.size());
		for (RpcResourceCategory rpcObject : rpcRoleList) {
			resultList.add(new ProxyResourceCategory(rpcObject));
		}
		return resultList;
	}

	@Override
	public void setResultList(List<ResourceCategory> resultList) {
		List<RpcResourceCategory> rpcObjectList = new ArrayList<>(resultList.size());
		for (ResourceCategory user : resultList) {
			rpcObjectList.add(new ProxyResourceCategory(user).toRpcResourceCategory());
		}
		roleQuery.setResultList(rpcObjectList);
	}
	
	public RpcResourceCategoryQuery toRpcResourceCategoryQuery(){
		return roleQuery;
	}
	
}
