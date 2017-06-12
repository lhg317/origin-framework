namespace java com.goldgov.origin.core.service.rpc

struct RpcPagingInfo {
	1: i32 pageSize = 15;
	2: i32 currentPage;
	3: i64 count;
	4: i32 maxPage;
	5: i32 minPage = 1;
	6: i32 firstResult = 0;
}

struct RpcSortInfo {
	1: string name;
	2: SortDirection direction;
}

enum SortDirection{
	ASC,DESC;
}
