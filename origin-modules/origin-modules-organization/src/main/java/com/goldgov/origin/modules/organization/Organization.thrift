namespace java com.goldgov.origin.modules.organization.api
include "RpcQuery.thrift"

struct RpcOrganization {
	1:string orgID;
	2:string orgName;
	3:string orgCode;
	4:string abbreviation;
	5:string nodePath;
	6:i32 nodeValue;
}

struct RpcOrganizationQuery {
	1: RpcQuery.RpcPagingInfo pagingInfo;
	2: list<RpcOrganization> resultList;
	3: i32 pageSize;
	4: i32 currentPage;
}

service RpcOrganizationService {
	string addOrganization(1:RpcOrganization organization);
	void updateOrganization(1:RpcOrganization organization);
	void deleteOrganization(1:list<string> ids);
	RpcOrganization getOrganization(1:string id);
	RpcOrganizationQuery listOrganization(1:RpcOrganizationQuery query);
}


