namespace java com.goldgov.origin.modules.resource.api

struct RpcResourceCategory {
	1: i32 categoryID,
	2: string categoryName,
	3: string dataPath,
	4: string description,
	5: i32 parentID,
	6: i32 orderNum,
}

struct RpcResource {
	1: i32 resourceID,
	2: string resourceName,
	3: string resourceCode,
	4: string description,
	5: RpcResourceCategory resourceCategory,
	6: list<RpcResourceOperate> resourceOperateList
}

enum RpcOperateType{
	Save,
	Delete,
	Update,
	Find,
	FindList,
	None
}

struct RpcResourceOperate {
	1: i32 operateID,
	2: string operateName,
	3: string operateCode,
	4: RpcOperateType operateType,
	5: i32 resourceID
}

struct RpcResourceCategoryQuery {
	1: i32 pageSize = 15;
	2: i32 currentPage;
	3: i64 count;
	4: i32 maxPage;
	5: i32 minPage = 1;
	6: i32 firstResult = 0;
	7: list<RpcResourceCategory> resultList;
}

struct RpcResourceQuery {
	1: i32 pageSize = 15;
	2: i32 currentPage;
	3: i64 count;
	4: i32 maxPage;
	5: i32 minPage = 1;
	6: i32 firstResult = 0;
	7: list<RpcResource> resultList;
}

service RpcResourceService{
	void addCategory(1:RpcResourceCategory category);
	void updateCategory(1:RpcResourceCategory category);
	void deleteCategory(1:list<i32> ids);
	RpcResourceCategory findCategory(1:i32 id);
	list<RpcResourceCategory> findResourceCategoryList(1:RpcResourceCategoryQuery query);
	void addResource(1:RpcResource resource);
	void addOperate(1:RpcResourceOperate operate);
	void updateResource(1:RpcResource user);
	void updateOperate(1:RpcResourceOperate operate);
	void deleteResource(1:list<i32> ids);
	RpcResource findResource(1:i32 id);
	RpcResourceOperate findOperate(1:i32 id);
	list<RpcResource> findResourceList(1:RpcResourceQuery query);
	list<RpcResourceOperate> findOperateList(1:RpcResourceQuery query);
}
