package com.goldgov.origin.modules.file.service;

/**
 * 文件管理接口，支持文件分片创建
 * @author LiuHG
 * @version 1.0.0
 */
public interface FileFragmentService extends FileService{

	/**
	 * 创建分片文件，但此方法并不实际创建分片文件，而是将文件原信息存入数据库
	 * @param fileMeta
	 * @param fragmentTotal 预留，目前实现未使用
	 * @return
	 */
	String createFileFragment(File fileMeta,int fragmentTotal);
	
	/**
	 * 创建分片文件
	 * @param fileMeta
	 * @param fragmentTotal 预留，目前实现未使用
	 * @param bytes
	 * @return
	 */
	String createFileFragment(File fileMeta,int fragmentTotal,byte[] bytes);
	
	void addFileFragment(String fileID,int fragment,byte[] bytes);
	
	void completeFileFragment(String fileID,int fragmentTotal);
	
	byte[] getFileFragmentContent(String fileID,long startIndex);
	
}
