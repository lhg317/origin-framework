package com.goldgov.origin.modules.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.goldgov.origin.modules.user.api.RpcUser;
import com.goldgov.origin.modules.user.api.RpcUserQuery;
import com.goldgov.origin.modules.user.service.User;
import com.goldgov.origin.modules.user.service.UserQuery;

public class ProxyUserQuery extends UserQuery {

	private RpcUserQuery userQuery;
	
	public ProxyUserQuery(){
		userQuery = new RpcUserQuery();
	}
	
	public ProxyUserQuery(RpcUserQuery userQuery){
		this.userQuery = userQuery;
	}

	@Override
	public int getPageSize() {
		return userQuery.getPageSize();
	}

	@Override
	public void setPageSize(int pageSize) {
		userQuery.setPageSize(pageSize);
	}

	@Override
	public int getCurrentPage() {
		return userQuery.getCurrentPage();
	}

	@Override
	public void setCurrentPage(int currentPage) {
		userQuery.setCurrentPage(currentPage);
	}

	@Override
	public long getCount() {
		return userQuery.getCount();
	}

	@Override
	public void setCount(long count) {
		userQuery.setCount(count);
	}

	@Override
	public int getMaxPage() {
		return userQuery.getMaxPage();
	}

	@Override
	public void setMaxPage(int maxPage) {
		userQuery.setMaxPage(maxPage);
	}

	@Override
	public void setFirstResult(int firstResult) {
		userQuery.setFirstResult(firstResult);
	}

	@Override
	public int getFirstResult() {
		return userQuery.getFirstResult();
	}

	@Override
	public int getMinPage() {
		return userQuery.getMinPage();
	}

	@Override
	public void setMinPage(int minPage) {
		userQuery.setMinPage(minPage);
	}

	@Override
	public List<User> getResultList() {
		List<RpcUser> rpcObjectList = userQuery.getResultList();
		List<User> resultList = new ArrayList<>(rpcObjectList.size());
		for (RpcUser rpcObject : rpcObjectList) {
			resultList.add(new ProxyUser(rpcObject));
		}
		return resultList;
	}

	@Override
	public void setResultList(List<User> resultList) {
		List<RpcUser> rpcObjectList = new ArrayList<>(resultList.size());
		for (User user : resultList) {
			rpcObjectList.add(new ProxyUser(user).toRpcUser());
		}
		userQuery.setResultList(rpcObjectList);
	}
	
	public RpcUserQuery toRpcQuery(){
		return userQuery;
	}
	
}
