package com.goldgov.origin.modules.role.service;

import java.util.ArrayList;
import java.util.List;

import com.goldgov.origin.core.service.rpc.RpcPagingInfo;
import com.goldgov.origin.modules.role.api.RpcRole;
import com.goldgov.origin.modules.role.api.RpcRoleQuery;

public class ProxyRoleQuery extends RoleQuery<Role> {

	private RpcRoleQuery roleQuery;
	
	public ProxyRoleQuery(){
		roleQuery = new RpcRoleQuery();
	}
	
	public ProxyRoleQuery(RpcRoleQuery roleQuery){
		this.roleQuery = roleQuery;
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

	@Override
	public List<Role> getResultList() {
		List<RpcRole> rpcRoleList = roleQuery.getResultList();
		List<Role> resultList = new ArrayList<>(rpcRoleList.size());
		for (RpcRole rpcObject : rpcRoleList) {
			resultList.add(new ProxyRole(rpcObject));
		}
		return resultList;
	}

	@Override
	public void setResultList(List<Role> resultList) {
		List<RpcRole> rpcObjectList = new ArrayList<>(resultList.size());
		for (Role user : resultList) {
			rpcObjectList.add(new ProxyRole(user).toRpcObject());
		}
		roleQuery.setResultList(rpcObjectList);
	}
	
	private RpcPagingInfo getPagingInfo(){
		RpcPagingInfo pagingInfo = roleQuery.getPagingInfo();
		if(pagingInfo == null){
			pagingInfo = new RpcPagingInfo();
			roleQuery.setPagingInfo(pagingInfo);
		}
		return pagingInfo;
	}
	
	public RpcRoleQuery toRpcQuery(){
		return roleQuery;
	}
	
}
