package com.goldgov.origin.modules.file.service;

import java.util.Arrays;

/**
 * 默认的上传配置，默认配置允许上传任何格式的文件，限制单个文件大小不得超过5MB
 * @author LiuHG
 * @version 1.0
 */
public class UploadConfig {

	private String[] fileExtension = {"*"};//允许的文件类型，例如：new String[]{"jpg","gif","png","bmp"}，new String[]{"*"}为所有文件。
	private Long sizeMax = 5242880L;//上传的单个文件大小限制（字节）
	private int uploadMax = 0;
	
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
	public int getUploadMax() {
		return uploadMax;
	}
	public void setUploadMax(int uploadMax) {
		this.uploadMax = uploadMax;
	}
	
	@Override
	public String toString() {
		return "UploadConfig [fileExtension=" + Arrays.toString(fileExtension) + ", sizeMax=" + sizeMax + ", uploadMax="
				+ uploadMax + "]";
	}
	
}
