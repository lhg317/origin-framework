package com.goldgov.origin.modules.file.dao;

import java.util.List;

import com.goldgov.origin.modules.file.service.File;

public interface FileDao {

	public void addFile(File fileMeta);
	
	public void deleteFile(String[] ids);
	
	public File getFile(String fileID);
	
	public List<File> listFile(String relationID);
}
