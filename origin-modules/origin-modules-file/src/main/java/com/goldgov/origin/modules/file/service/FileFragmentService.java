package com.goldgov.origin.modules.file.service;

public interface FileFragmentService {

	String createFileFragment(File fileMeta);
	
	String createFileFragment(File fileMeta,byte[] bytes);
	
	void addFileFragment(String fileID,byte[] bytes);
	
	void finishFileFragment(String fileID);
	
	void deleteFileFragment(String fileID);
}
