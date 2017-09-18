namespace java com.goldgov.origin.modules.file.api

struct RpcFile {
	1: optional string fileID;
	2: optional string fileName;
	3: optional i64 fileSize;
	4: optional string fileType;
	5: optional i64 createDate;
	6: optional string groupID;
	7: optional i64 downloadNum;
}

service RpcFileService {
	string addFile(1:RpcFile file,2:binary bytes);
	void deleteFile(1:list<string> ids);
	RpcFile getFile(1:string fileID);
	binary getFileContent(1:string fileID);
	list<RpcFile> listFile(1:string groupID);
	binary getImage(1:string fileID,2:i32 width,3:i32 height)
}

service RpcFileFragmentService extends RpcFileService{
	string createEmptyFileFragment(1:RpcFile file,2:i32 fragmentTotal);
	string createFileFragment(1:RpcFile file,2:i32 fragmentTotal,3:binary bytes);
	void addFileFragment(1:string fileID,2:i32 fragment,3:binary bytes);
	void completeFileFragment(1:string fileID,2:i32 fragmentTotal);
	binary getFileFragmentContent(1:string fileID,2:i64 startIndex);
}

