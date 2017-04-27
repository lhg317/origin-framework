package com.goldgov.origin.modules.file.service.impl;

import java.util.Date;

import com.goldgov.origin.modules.file.api.RpcFile;
import com.goldgov.origin.modules.file.service.File;

public class ProxyFile extends File{

	private RpcFile file;
	
	public ProxyFile(){
		file = new RpcFile();
	}
	
	public ProxyFile(RpcFile file){
		this.file = file;
	}
	
	public ProxyFile(File _file){
		this();
		if(_file == null){return;}
		setFileID(_file.getFileID());
		setFileName(_file.getFileName());
		setFileSize(_file.getFileSize());
		setFileType(_file.getFileType());
		setDownloadNum(_file.getDownloadNum());
		setCreateDate(_file.getCreateDate());
	}
	
	public String getFileID() {
		return file.getFileID();
	}
	public void setFileID(String fileID) {
		file.setFileID(fileID);
	}
	public String getFileName() {
		return file.getFileName();
	}
	public void setFileName(String fileName) {
		file.setFileName(fileName);
	}
	public Long getFileSize() {
		return file.getFileSize();
	}
	public void setFileSize(Long fileSize) {
		file.setFileSize(fileSize);
	}
	public String getFileType() {
		return file.getFileType();
	}
	public void setFileType(String fileType) {
		file.setFileType(fileType);
	}
	public String getRelationID() {
		return file.getRelationID();
	}
	public void setRelationID(String relationID) {
		file.setRelationID(relationID);
	}
	public Long getDownloadNum() {
		return file.getDownloadNum();
	}
	public void setDownloadNum(Long downloadNum) {
		file.setDownloadNum(downloadNum);
	}
	public Date getCreateDate() {
		return new Date(file.getCreateDate());
	}
	public void setCreateDate(Date createDate) {
		file.setCreateDate(createDate.getTime());
	}
	
	public RpcFile toRpcFile(){
		return file;
	}
	
}
