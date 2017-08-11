package com.goldgov.origin.modules.organization.service;
import java.util.List;
import com.goldgov.origin.modules.organization.api.RpcOrganizationQuery;
import com.goldgov.origin.modules.organization.service.impl.OrganizationConverter;
import com.goldgov.origin.core.discovery.rpc.ResultSetUtils;
import com.goldgov.origin.core.service.rpc.RpcPagingInfo;

/**
 * 组织机构管理
 * @author LiuHG
 *
 */
public class ProxyOrganizationQuery extends OrganizationQuery {
	
	private RpcOrganizationQuery rpcOrganizationQuery;
	private OrganizationConverter converter=new OrganizationConverter();
	 
	public ProxyOrganizationQuery(){
		rpcOrganizationQuery=new RpcOrganizationQuery();
	}
	
	public ProxyOrganizationQuery(RpcOrganizationQuery rpcOrganizationQuery){
		this.rpcOrganizationQuery=rpcOrganizationQuery;
	}
	
	public ProxyOrganizationQuery(OrganizationQuery organizationQuery){
		rpcOrganizationQuery=new RpcOrganizationQuery();
		rpcOrganizationQuery.setPagingInfo(new RpcPagingInfo(organizationQuery.getPageSize(), organizationQuery.getCurrentPage(), organizationQuery.getCount(), organizationQuery.getMaxPage(), organizationQuery.getMinPage(), organizationQuery.getFirstResult()));
		rpcOrganizationQuery.setResultList(ResultSetUtils.convertToRpc(organizationQuery.getResultList(),converter));
	}
	public List<Organization> getResultList() {
		return ResultSetUtils.convertFormRpc(rpcOrganizationQuery.getResultList(),converter);
	};
	public void setResultList(List<Organization> resultList) {
		rpcOrganizationQuery.setResultList(ResultSetUtils.convertToRpc(resultList,converter));
	};

	public int getPageSize() {
		return rpcOrganizationQuery.getPageSize()==0?null:rpcOrganizationQuery.getPageSize();
	}

	public void setPageSize(Integer pageSize) {
		rpcOrganizationQuery.setPageSize(pageSize);
	}

	public int getCurrentPage() {
		return rpcOrganizationQuery.getCurrentPage();
	}

	public void setCurrentPage(Integer currentPage) {
		rpcOrganizationQuery.setCurrentPage(currentPage);
	}
	
	
}
