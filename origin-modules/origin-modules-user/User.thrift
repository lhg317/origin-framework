namespace java com.goldgov.origin.modules.user.api

struct RpcUser {
	1: i32 userID;
	2: string userName;
	3: string loginName;
	4: string password;
	5: string email;
}

struct RpcUserQuery {
	1: i32 pageSize = 15;
	2: i32 currentPage;
	3: i64 count;
	4: i32 maxPage;
	5: i32 minPage = 1;
	6: i32 firstResult = 0;
	7: list<RpcUser> resultList;
}

exception RpcUserExistException{
	1: string loginName;
}

exception RpcUserNameCheckFailException{
	1: string userName;
}


service RpcUserService {
	void addUser(1:RpcUser user) throws (1:RpcUserExistException e1,2: RpcUserNameCheckFailException e2);
	void deleteUsers(1:list<i32> ids);
	void updateUser(1:RpcUser user);
	RpcUser findUserByID(1:i32 userID);
	RpcUser findUserByLoginName(1:string loginName);
	RpcUserQuery findUsers(1:RpcUserQuery userQuery);
	bool existUser(1:string loginName);
	bool updatePassword(1:string loginName,2:string oldPassword,3:string newPassword);
	bool checkUserName(1:string userName);
}

/*
* API定义规范：
* 添加方法以add开头，更新方法以update开头，删除方法以delete开头，查询方法find以开头（无参数的查询以get开头），其余方法以“动名”词的方式进行命名
* 批量操作，方法名以名次复数为名（不是单纯的加s），不要以List为方法名。正确：findUsers，错误：findUserList
* 条件查询的方法不使用“ByXXX”的形式命名。（一些特殊情况，MyBatis的Dao不遵循此条规则）
* 为与一般业务对象区分，API对象命名以Rpc开头
*/