namespace java com.goldgov.origin.modules.role.api

include "RpcQuery.thrift"

struct RpcRole {
	1: string roleID,
	2: string roleName,
	3: string roleCode,
	4: string description,
}

struct RpcRoleResource {
	1: string roleResourceID,
	2: string roleID,
	3: string resourceOperate,
}

struct RpcRoleQuery {
	1: RpcQuery.RpcPagingInfo pagingInfo;
	7: list<RpcRole> resultList;
}

struct RpcRoleObject{
	1: string roleObjectID,
	2: string roleID,
	3: string roleObject,
	4: string type;
}

service RpcRoleService{
	void addRole(1:RpcRole user),
	void deleteRole(1:list<string> ids),
	void updateRole(1:RpcRole role),
	RpcRole getRole(1:string roleID),
	RpcRoleQuery listRole(1:RpcRoleQuery query),
	void saveRoleResource(1:string roleID,2:list<string> resourceOperate),
	void saveRoleObject(1:string roleID,2:list<string> roleObject,3: string roleObjectType),
	list<RpcRole> listRoleByObject(1:string roleObject),
	list<RpcRoleResource> listRoleResourceByObject(1:string roleObject),	
	map<string,list<string>> getRoleResourceMap();
	list<RpcRoleResource> listRoleResourceByRoleID(1:string roleID);
} 