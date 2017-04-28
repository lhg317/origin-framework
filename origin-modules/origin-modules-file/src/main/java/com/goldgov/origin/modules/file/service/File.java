package com.goldgov.origin.modules.file.service;

import java.util.Date;

public class File {

	public static final int FILE_STATE_INIT = 1;
	public static final int FILE_STATE_COMPLETE = 2;
	public static final int FILE_STATE_ASSOCIATED = 3;
	
	private String fileID;
	private String fileName;
	private Long fileSize;
	private String fileType;
	private String relationID;
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
	public String getRelationID() {
		return relationID;
	}
	public void setRelationID(String relationID) {
		this.relationID = relationID;
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
	
}
