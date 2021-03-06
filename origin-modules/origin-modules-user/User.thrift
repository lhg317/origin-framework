namespace java com.goldgov.origin.modules.user.api

include "RpcQuery.thrift"

struct RpcUser {
	1: string userID;
	2: string userName;
	3: string loginName;
	4: string email;
	5: string phone;
}

struct RpcUserQuery {
	/*1: i32 pageSize = 15;
	2: i32 currentPage;
	3: i64 count;
	4: i32 maxPage;
	5: i32 minPage = 1;
	6: i32 firstResult = 0;
	7: list<RpcUser> resultList;
	*/
	1: RpcQuery.RpcPagingInfo pagingInfo;
	2: list<RpcUser> resultList;
}

exception RpcUserExistException{
	1: string loginName;
}

exception RpcUserNameCheckFailException{
	1: string userName;
}


service RpcUserService {
	string addUser(1:RpcUser user) throws (1:RpcUserExistException e1,2: RpcUserNameCheckFailException e2);
	void deleteUser(1:list<string> ids);
	void updateUser(1:RpcUser user);
	RpcUser getUser(1:string userID);
	RpcUser getUserByLoginName(1:string loginName);
	RpcUserQuery listUser(1:RpcUserQuery userQuery);
	bool existUser(1:string loginName);
	bool checkUserName(1:string userName);
}

/*
* API定义规范：
* 添加方法以add开头，更新方法以update开头，删除方法以delete开头，保存更新方法以save开头，查询方法find以开头（无参数的查询以get开头），其余方法以“动名”词的方式进行命名
* 批量操作，方法名以名次复数为名（不是单纯的加s），不要以List为方法名。正确：findUsers，错误：findUserList
* 条件查询的方法不使用“ByXXX”的形式命名。（一些特殊情况，MyBatis的Dao不遵循此条规则）
* 为与一般业务对象区分，API对象命名以Rpc开头
*/