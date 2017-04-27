package com.goldgov.origin.modules.file.service;

public interface FileFragmentService extends FileService{

	String createFileFragment(File fileMeta,int fragmentTotal);
	
	String createFileFragment(File fileMeta,int fragmentTotal,byte[] bytes);
	
	void addFileFragment(String fileID,int fragment,byte[] bytes);
	
	void completeFileFragment(String fileID,int fragmentTotal);
	
}
