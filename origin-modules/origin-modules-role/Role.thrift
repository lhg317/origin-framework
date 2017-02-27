include "Resource.thrift"

namespace java com.goldgov.origin.modules.role.api

struct RpcRole {
	1: i32 roleID,
	2: string roleName,
	3: string roleCode,
	4: string description,
	5: i32 roleType,
	6: i32 isEnable,
	
}

struct RpcRoleResource {
	1: i32 roleResourceID,
	2: RpcRole role,
	3: Resource.RpcResource resource,
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
	2: i32 objectType,
	3: string objectID,
	4: string objectName,
	5: RpcRole role
}

service RpcRoleService{
	void addRole(1:RpcRole user),
	void deleteRole(1:list<i32> ids),
	void updateRole(1:RpcRole role),
	RpcRole findRoleByID(1:i32 roleID),
	RpcRoleQuery findRoleList(1:RpcRoleQuery query),
	
} 