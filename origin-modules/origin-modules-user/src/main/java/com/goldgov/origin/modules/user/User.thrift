namespace java com.goldgov.origin.modules.user.api

struct RpcUser {
	1: i32 userID,
	2: string userName,
	3: string loginID,
	4: string password,
	5: string email
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

service RpcUserService{
	void addUser(1:RpcUser user),
	void deleteUser(1:list<i32> ids),
	void updateUser(1:RpcUser user),
	RpcUser findUserByID(1:i32 userID),
	RpcUserQuery findUserList(1:RpcUserQuery userQuery),
	
} 