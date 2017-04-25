package com.goldgov.origin.modules.file.service;

import java.io.InputStream;
import java.util.List;

public interface FileService {

	void addFile(String serviceName,File fileMeta,InputStream content);
	
	void deleteFile(String serviceName,String[] ids);
	
	File getFile(String serviceName,String fileID);
	
	InputStream getFileContent(String serviceName,String fileID);
	
	List<File> listFile(String serviceName,String relationID);
	
}
