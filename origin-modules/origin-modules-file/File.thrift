namespace java com.goldgov.origin.modules.file.api

struct RpcFile {
	1: string fileID;
	2: string fileName;
	3: i64 fileSize;
	4: string fileType;
	5: string relationID;
}

service RpcFileService {
	void addFile(1:RpcFile file,2:binary bytes);
	void deleteFile(1:list<string> ids);
	RpcFile getFile(1:string fileID);
	binary getFileContent(1:string fileID);
	list<RpcFile> listFile(1:string relationID);
}
