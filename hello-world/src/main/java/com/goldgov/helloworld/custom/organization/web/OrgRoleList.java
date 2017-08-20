package com.goldgov.helloworld.custom.organization.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.goldgov.origin.modules.organization.api.RpcOrganization;
import com.goldgov.origin.modules.organization.api.RpcOrganizationService;
import com.goldgov.origin.modules.role.api.ObjectRoleList;
import com.goldgov.origin.modules.role.api.RpcRole;
import com.goldgov.origin.modules.role.api.RpcRoleService;

@Component
public class OrgRoleList implements ObjectRoleList{

	@Autowired
	@Qualifier("rpcOrganizationService.Client")
	private RpcOrganizationService.Iface orgService;
	
	@Autowired
	@Qualifier("rpcRoleService.Client")
	private RpcRoleService.Iface roleService;
	
	
	@Override
	public List<RpcRole> listRole(String loginName) throws Exception {
		List<RpcOrganization> orgList = orgService.listOrganizationByUser(loginName);
		ArrayList<RpcRole> roleList = new ArrayList<>();
		//FIXME 假设一个人不会处于太多机构下，否则不建议循环查询
		for (RpcOrganization rpcOrganization : orgList) {
			List<RpcRole> orgRoleList = roleService.listRoleByObject(rpcOrganization.getOrgID(), "ORGANIZATION");
			for(RpcRole rpcRole : orgRoleList){
				if(!isExist(orgRoleList,rpcRole.getRoleCode())){
					roleList.add(rpcRole);
				}
			}
		}
		return roleList;
	}
	
	private boolean isExist(List<RpcRole> orgRoleList,String roleCode){
		for (RpcRole rpcRole : orgRoleList) {
			if(rpcRole.equals(roleCode)){
				return true;
			}
		}
		return false;
	}

}
