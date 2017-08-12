package com.goldgov.origin.modules.file.service;

import java.io.InputStream;
import java.util.List;

public interface FileService {

	void addFile(File fileMeta,InputStream content);
	
	void deleteFile(String[] ids);
	
	void deleteFileByRelationID(String relationID);
	
	File getFile(String fileID);
	
	InputStream getFileContent(String fileID);
	
	List<File> listFile(String groupID);
	
	void upldateRelationID(String relationID,String[] fileIDs);
	
}
