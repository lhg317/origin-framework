package com.goldgov.origin.modules.file.service;

import java.io.InputStream;

public interface FileStorage {

	void saveFile(String fileID,InputStream fileStream);
	InputStream loadFile(String fileID);
	void deleteFile(String[] fileIDs);
	
}
