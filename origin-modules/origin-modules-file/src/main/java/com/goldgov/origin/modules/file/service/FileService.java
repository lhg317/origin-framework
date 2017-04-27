package com.goldgov.origin.modules.file.service;

import java.io.InputStream;
import java.util.List;

public interface FileService {

	void addFile(File fileMeta,InputStream content);
	
	void deleteFile(String[] ids);
	
	File getFile(String fileID);
	
	InputStream getFileContent(String fileID);
	
	List<File> listFile(String relationID);
	
	void upldateRelationID(String relationID,String[] fileIDs);
	
}
