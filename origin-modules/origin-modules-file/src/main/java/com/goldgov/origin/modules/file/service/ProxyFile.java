package com.goldgov.origin.modules.file.service;

import java.util.Date;

import com.goldgov.origin.modules.file.api.RpcFile;

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
		setGroupID(_file.getGroupID());
	}
	
	public String getFileID() {
		if(file.isSetFileID()){
			return file.getFileID();
		}else{
			return null;
		}
	}
	public void setFileID(String fileID) {
		if(fileID != null){
			file.setFileID(fileID);
		}
	}
	public String getFileName() {
		if(file.isSetFileName()){
			return file.getFileName();
		}else{
			return null;
		}
	}
	public void setFileName(String fileName) {
		if(fileName != null){
			file.setFileName(fileName);
		}
	}
	public Long getFileSize() {
		if(file.isSetFileSize()){
			return file.getFileSize();
		}else{
			return null;
		}
	}
	public void setFileSize(Long fileSize) {
		if(fileSize != null){
			file.setFileSize(fileSize);
		}
	}
	public String getFileType() {
		if(file.isSetFileType()){
			return file.getFileType();
		}else{
			return null;
		}
	}
	public void setFileType(String fileType) {
		if(fileType != null){
			file.setFileType(fileType);
		}
	}
	public String getGroupID() {
		if(file.isSetGroupID()){
			return file.getGroupID();
		}else{
			return null;
		}
	}
	public void setGroupID(String groupID) {
		if(groupID != null){
			file.setGroupID(groupID);
		}
	}
//	public Long getDownloadNum() {
//		return file.getDownloadNum();
//	}
//	public void setDownloadNum(Long downloadNum) {
//		file.setDownloadNum(downloadNum);
//	}
	public Date getCreateDate() {
		if(file.isSetCreateDate()){
			return new Date(file.getCreateDate());
		}else{
			return null;
		}
	}
	public void setCreateDate(Date createDate) {
		if(createDate != null){
			file.setCreateDate(createDate.getTime());
		}
	}
	
	public RpcFile toRpcFile(){
		return file;
	}
	
}
