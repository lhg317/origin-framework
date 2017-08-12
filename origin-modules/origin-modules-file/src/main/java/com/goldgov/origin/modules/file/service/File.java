package com.goldgov.origin.modules.file.service;

import java.util.Date;

public class File {

	private String fileID;
	private String fileName;
	private Long fileSize;
	private String fileType;
	private String groupID;
	private Long downloadNum;
	private Date createDate;
	
	public String getFileID() {
		return fileID;
	}
	public void setFileID(String fileID) {
		this.fileID = fileID;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Long getDownloadNum() {
		return downloadNum;
	}
	public void setDownloadNum(Long downloadNum) {
		this.downloadNum = downloadNum;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	
}
