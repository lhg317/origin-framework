package com.goldgov.origin.modules.user.service.impl;

import com.goldgov.origin.core.service.rpc.RpcPagingInfo;
import com.goldgov.origin.modules.user.api.RpcUserQuery;
import com.goldgov.origin.modules.user.service.UserQuery;

public class ProxyUserQuery extends UserQuery {

	private RpcUserQuery userQuery;
//	private RpcPagingInfo pagingInfo;
//	private RpcSortInfo[] sortInfo;
	
	public ProxyUserQuery(){
		userQuery = new RpcUserQuery();
	}
	
	public ProxyUserQuery(RpcUserQuery userQuery){
		this.userQuery = userQuery;
	}

	@Override
	public int getPageSize() {
		return getPagingInfo().getPageSize();
	}

	@Override
	public void setPageSize(int pageSize) {
		getPagingInfo().setPageSize(pageSize);
	}

	@Override
	public int getCurrentPage() {
		return getPagingInfo().getCurrentPage();
	}

	@Override
	public void setCurrentPage(int currentPage) {
		getPagingInfo().setCurrentPage(currentPage);
	}

	@Override
	public long getCount() {
		return getPagingInfo().getCount();
	}

	@Override
	public void setCount(long count) {
		getPagingInfo().setCount(count);
	}

	@Override
	public int getMaxPage() {
		return getPagingInfo().getMaxPage();
	}

	@Override
	public void setMaxPage(int maxPage) {
		getPagingInfo().setMaxPage(maxPage);
	}

	@Override
	public void setFirstResult(int firstResult) {
		getPagingInfo().setFirstResult(firstResult);
	}

	@Override
	public int getFirstResult() {
		return getPagingInfo().getFirstResult();
	}

	@Override
	public int getMinPage() {
		return getPagingInfo().getMinPage();
	}

	@Override
	public void setMinPage(int minPage) {
		getPagingInfo().setMinPage(minPage);
	}
	
	private RpcPagingInfo getPagingInfo(){
		RpcPagingInfo pagingInfo = userQuery.getPagingInfo();
		if(pagingInfo == null){
			pagingInfo = new RpcPagingInfo();
			userQuery.setPagingInfo(pagingInfo);
		}
		return pagingInfo;
	}

//	@Override
//	public List<User> getResultList() {
//		List<RpcUser> rpcObjectList = userQuery.getResultList();
//		List<User> resultList = new ArrayList<>(rpcObjectList.size());
//		for (RpcUser rpcObject : rpcObjectList) {
//			resultList.add(new ProxyUser(rpcObject));
//		}
//		return resultList;
//	}
//
//	@Override
//	public void setResultList(List<User> resultList) {
//		List<RpcUser> rpcObjectList = new ArrayList<>(resultList.size());
//		for (User user : resultList) {
//			rpcObjectList.add(new ProxyUser(user).toRpcUser());
//		}
//		userQuery.setResultList(rpcObjectList);
//	}
	
}
