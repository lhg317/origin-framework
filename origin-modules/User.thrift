namespace java com.goldgov.origin.modules.user.api

struct RpcUser {
	1: i32 userID,
	2: string userName,
	3: string loginID,
	4: string password,
	5: string email
}

service RpcUserService{
	void addUser(1:RpcUser user),
	void deleteUser(1:list<i32> ids),
	void updateUser(1:RpcUser user),
	RpcUser findUserByID(1:i32 userID),
	list<RpcUser> findUserList(),
} 