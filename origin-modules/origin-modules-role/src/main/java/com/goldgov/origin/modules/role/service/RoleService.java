package com.goldgov.origin.modules.role.service;

import java.util.List;
import java.util.Map;

/**
 * 角色相关业务接口
 * @author LiuHG
 * @version 1.0
 */
public interface RoleService {

	public void addRole(Role role);
	
	void saveRoleResource(String roleID,String[] resourceOperate);
	
	void saveRoleObject(String roleID,String[] roleObject,String roleObjectType);
	
	void updateRole(Role role);
	
	void deleteRole(String[] ids);
	
	void deleteRoleResource(String[] ids);
	
	Role getRole(String id);
	
	/**
	 * 根据查询器对象进行条件查询角色对象，可处理分页
	 * @param roleQuery 角色查询器对象
	 * @return 满足条件的所有角色对象
	 */
	List<Role> listRole(RoleQuery<Role> roleQuery);
	
	/**
	 * 根据角色关联对象（例如用户ID），查询该角色对象下的所有资源。
	 * @param roleObject 角色关联对象，可能为比如用户ID等通用的、表示角色拥有者特征的唯一标识。
	 * @return 关联对象所属的所有资源对象
	 */
	List<RoleResource> listRoleResourceByObject(String roleObject);
	List<RoleResource> listRoleResourceByObject(String roleObject,String roleObjectType);

	/**
	 * 根据角色关联对象（例如用户ID），查询该角色对象下的所有所属角色。
	 * @param roleObject 角色关联对象，可能为比如用户ID等通用的、表示角色拥有者特征的唯一标识。
//	 * @param roleObjectType 角色对象类型
	 * @return 关联对象所属的所有角色对象
	 */
	List<Role> listRoleByObject(String roleObject);
	List<Role> listRoleByObject(String roleObject,String roleObjectType);
	
	/**
	 * 获取角色资源，以Map形式返回。
	 * Map的存放格式为：key为角色编码，value为与角色对应的资源操作编码List集合。
	 * @return 角色与资源操作对应的Map对象
	 */
	Map<String,List<String>> getRoleResourceMap();
	
	
//	/**
//	 * 将角色与资源操作封装成Map形式并放入到缓存中。
//	 * Map的存放格式为：key为角色编码，value为与角色对应的资源操作编码List集合。
//	 * 本方法主要是将{@link #getRoleResourceMap()}方法返回的Map放入缓存。如果有特殊场景，
//	 * 可以在其他节点自行调用{@link #getRoleResourceMap()}方法，将返回值单独处理、缓存。
//	 */
//	void refreshRoleResourceCache();

	
}
