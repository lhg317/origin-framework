package com.goldgov.origin.modules.file.service;

import java.io.InputStream;

public interface FileStorage {

	public void saveFile(String fileID,InputStream fileStream);
	public InputStream loadFile(String fileID);
	public void deleteFile(String[] fileIDs);
}
