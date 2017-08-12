package com.goldgov.origin.modules.organization.service;

import com.goldgov.origin.core.service.rpc.RpcPagingInfo;
import com.goldgov.origin.modules.organization.api.RpcOrganizationQuery;

/**
 * 组织机构管理
 * @author LiuHG
 *
 */
public class ProxyOrganizationQuery extends OrganizationQuery {
	
	private RpcOrganizationQuery rpcQuery;
	
	public ProxyOrganizationQuery(){
		rpcQuery = new RpcOrganizationQuery();
	}
	
	public ProxyOrganizationQuery(RpcOrganizationQuery query){
		this.rpcQuery = query;
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
		RpcPagingInfo pagingInfo = rpcQuery.getPagingInfo();
		if(pagingInfo == null){
			pagingInfo = new RpcPagingInfo();
			rpcQuery.setPagingInfo(pagingInfo);
		}
		return pagingInfo;
	}
	
}
