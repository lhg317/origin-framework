package com.goldgov.origin.modules.file.service;

import java.io.InputStream;
import java.util.List;

public interface FileService {

	void addFile(File fileMeta,InputStream content);
	
	void deleteFile(String[] ids);
	
	void deleteFileByGroupID(String groupID);
	
	File getFile(String fileID);
	
	InputStream getFileContent(String fileID);
	
	List<File> listFile(String groupID);
	
	void updateGroupID(String groupID,String[] fileIDs);
	
	void addFileToStorage(String fileID, InputStream content);
	
}
