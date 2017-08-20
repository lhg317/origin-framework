package com.goldgov.origin.modules.role.service;

import java.util.List;

/**
 * 
 * @author LiuHG
 * @version 1.0
 *
 */
@Deprecated
public interface RoleObjectResource {

	public String typeCode();
	
	/**
	 * 
	 * @param roleObjectCode
	 * @return 角色集合
	 */
	public List<Role> listRole(String roleObjectCode);
	
	public List<RoleResource> listRoleResource(String roleObjectCode);
}
