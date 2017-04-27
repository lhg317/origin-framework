namespace java com.goldgov.origin.modules.file.api

struct RpcFile {
	1: string fileID;
	2: string fileName;
	3: i64 fileSize;
	4: string fileType;
	5: i64 createDate;
	6: string relationID;
	7: i64 downloadNum;
}

service RpcFileService {
	string addFile(1:RpcFile file,2:binary bytes);
	void deleteFile(1:list<string> ids);
	RpcFile getFile(1:string fileID);
	binary getFileContent(1:string fileID);
	list<RpcFile> listFile(1:string relationID);
}

service RpcFileFragmentService extends RpcFileService{
	string createEmptyFileFragment(1:RpcFile file,2:i32 fragmentTotal);
	string createFileFragment(1:RpcFile file,2:i32 fragmentTotal,3:binary bytes);
	void addFileFragment(1:string fileID,2:i32 fragment,3:binary bytes);
	void completeFileFragment(1:string fileID,2:i32 fragmentTotal);
}

