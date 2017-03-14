#include "Resource.thrift"

namespace java com.goldgov.origin.modules.role.api

struct RpcRole {
	1: i32 roleID,
	2: string roleName,
	3: string roleCode,
	4: string description,
}

struct RpcRoleResource {
	1: i32 roleResourceID,
	2: i32 roleID,
	3: string resourceOperate,
}

struct RpcRoleQuery {
	1: i32 pageSize = 15;
	2: i32 currentPage;
	3: i64 count;
	4: i32 maxPage;
	5: i32 minPage = 1;
	6: i32 firstResult = 0;
	7: list<RpcRole> resultList;
}

struct RpcRoleObject{
	1: i32 roleObjectID,
	2: i32 roleID,
	3: string roleObject,
}

service RpcRoleService{
	void addRole(1:RpcRole user),
	void deleteRoles(1:list<i32> ids),
	void updateRole(1:RpcRole role),
	RpcRole findRoleByID(1:i32 roleID),
	RpcRoleQuery findRoles(1:RpcRoleQuery query),
	void saveRoleResources(1:i32 roleID,2:list<string> resourceOperate),
	void saveRoleObjects(1:i32 roleID,2:list<string> roleObject),
	list<RpcRole> findRolesByObject(1:string roleObject),
	list<RpcRoleResource> findRoleResourcesByObject(1:string roleObject),
	void initRoleResourcesMap();
} 