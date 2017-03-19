package com.goldgov.origin.modules.role.api;

import java.util.ArrayList;
import java.util.List;

import com.goldgov.origin.modules.role.service.Role;
import com.goldgov.origin.modules.role.service.RoleQuery;

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
			rpcObjectList.add(new ProxyRole(user).toRpcRole());
		}
		roleQuery.setResultList(rpcObjectList);
	}
	
	public RpcRoleQuery toRpcQuery(){
		return roleQuery;
	}
	
}
