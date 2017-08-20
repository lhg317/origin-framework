package com.goldgov.origin.modules.role.api;

import java.util.List;

public interface ObjectRoleList {

	public List<RpcRole> listRole(String loginName) throws Exception;
}
