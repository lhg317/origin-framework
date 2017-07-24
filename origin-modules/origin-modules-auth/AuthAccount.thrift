namespace java com.goldgov.origin.modules.auth.api

struct RpcAuthAccount {
	1: string accountID;
	2: string principal;
	3: string password;
	4: i64 expiredDate;
	5: bool locked=false;
	6: i64 passwordExpired;
	7: bool enabled=true;
}

service RpcAuthAccountService {
	void addAuthAccount(1:RpcAuthAccount authAccount);
	
	RpcAuthAccount getAuthAccount(1:string principal, 2:string password);
	
	void updatePassword(1:string principal,2:string oldPass,3:string newPass);
	
	void updateExpiredDate(1:string principal,2:i64 expiredDate);
	
	void setEnabled(1:string principal,2:bool enabled);
	
	void setLocked(1:string principal,2:bool locked);
	
	void deleteAuthAccount(1:list<string> principal);
	
	string getPassword(1:string principal);
	
}