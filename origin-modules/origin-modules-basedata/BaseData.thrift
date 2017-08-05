namespace java com.goldgov.origin.modules.basedata.api

struct RpcBaseDataLocale {
	1: optional  string localeID;
	2: optional  string localeCode;
	3: optional  string localeName;
}

struct RpcBaseData{
	1: optional string dataID;
	2: optional string dataName;
	3: optional string dataValue;
	4: optional string description;
	5: optional i32 orderNum;
	6: optional RpcBaseDataLocale dataLocale;
}

service RpcBaseDataService {
	
	string addLocale(1:RpcBaseDataLocale locale);
	
	RpcBaseDataLocale getLocale(1:string localeID);
	
	void updateLocale(1: RpcBaseDataLocale locale);
	
	void deleteLocale(1:string localeID);
	
	list<RpcBaseDataLocale> listLocale();
	
	string addData(1:RpcBaseData data);
	
	RpcBaseData getData(1:string dataID);
	
	void updateData(1:RpcBaseData data);
	
	void deleteData(1:list<string> dataID);
	
	list<RpcBaseData> listData(1:string localeCode,2:string dataName);
}