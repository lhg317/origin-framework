namespace java com.goldgov.origin.modules.organization.api
include "RpcQuery.thrift"

struct RpcOrganization {
	1:optional string orgID;
	2:optional string orgName;
	3:optional string orgCode;
	4:optional string abbreviation;
	5:optional string nodePath;
	6:optional i32 nodeValue;
	7:optional RpcOrganization parentOrganization;
}

struct RpcOrganizationPost {
	1:optional string orgPostID;
	2:optional string postName;
	3:optional string postCode;
	4:optional RpcOrganization organization;
}

struct RpcOrganizationUser {
	1:optional string orgUserID;
	2:optional string orgUser;
	3:optional RpcOrganization organization;
}

struct RpcPostUser {
	1:optional string postUserID;
	2:optional RpcOrganizationUser orgUser;
	3:optional RpcOrganizationPost orgPost;
}

struct RpcOrganizationQuery {
	1: optional RpcQuery.RpcPagingInfo pagingInfo;
	2: optional list<RpcOrganization> resultList;
}

service RpcOrganizationService {
	string addOrganization(1:RpcOrganization organization);
	void updateOrganization(1:RpcOrganization organization);
	void deleteOrganization(1:list<string> ids);
	RpcOrganization getOrganization(1:string id);
	RpcOrganizationQuery listOrganization(1:RpcOrganizationQuery query);
	
	void addOrgUser(1:string orgID,2:list<string> users);
	void deleteOrgUser(1:list<string> orgUserID);
	void deleteOrgUserByUser(1:string orgID,2:list<string> users);
	
	void addOrganizationPost(1:RpcOrganizationPost post);
	void deleteOrganizationPost(1:list<string> ids);
	void updateOrganizationPost(1:RpcOrganizationPost post);
	list<RpcOrganizationPost> listOrganizationPost(1:string orgID);
	RpcOrganizationPost getOrganizationPost(1:string id);
	
	void addPostUser(1:string postID,2:list<string> users);
	void deletePostUser(1:list<string> postUserID);
	void deletePostUserByUser(1:string postID,2:list<string> users);
}


