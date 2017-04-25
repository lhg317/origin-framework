package com.goldgov.origin.modules.file.service;

public class UploadConfig {

//	private String code;//业务编码
	private String[] fileExtension = {"*"};//允许的文件类型，例如：new String[]{"jpg","gif","png","bmp"}，new String[]{"*"}为所有文件。
	private Long sizeMax = 5242880L;//上传的单个文件大小限制（字节）
	private boolean checkReferer = true;//是否检查Referer用于防止地址栏输入地址直接下载
	
	public String[] getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String[] fileExtension) {
		this.fileExtension = fileExtension;
	}
	public Long getSizeMax() {
		return sizeMax;
	}
	public void setSizeMax(Long sizeMax) {
		this.sizeMax = sizeMax;
	}
	public boolean isCheckReferer() {
		return checkReferer;
	}
	public void setCheckReferer(boolean checkReferer) {
		this.checkReferer = checkReferer;
	}
	
	
}
