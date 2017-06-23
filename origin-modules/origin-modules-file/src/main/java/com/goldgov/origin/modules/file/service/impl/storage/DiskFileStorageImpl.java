package com.goldgov.origin.modules.file.service.impl.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import com.goldgov.origin.modules.file.service.FileStorage;

@Component("DiskFileStorage")
public class DiskFileStorageImpl implements FileStorage {

	private Log logger = LogFactory.getLog(getClass());
	
	@Value("${modules.file.base-path:}")
	private String basePath;
	
	private File basePathDirectory;
	
	@Override
	public void saveFile(String fileID, InputStream fileStream) {
		File file = new File(getBasePathDirectory(),fileID);
		try {
			FileCopyUtils.copy(fileStream, new FileOutputStream(file));
		} catch (IOException e) {
			throw new RuntimeException("保存文件时出现错误：" + file.getPath(),e);
		}
	}

	@Override
	public InputStream loadFile(String fileID) {
		File file = new File(getBasePathDirectory(),fileID);
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("获取文件错误，文件不存在：" + file.getPath(),e);
		}
	}

	@Override
	public void deleteFile(String[] fileIDs) {
		for (String fileID : fileIDs) {
			File file = new File(getBasePathDirectory(),fileID);
			boolean success = file.delete();
			if(logger.isWarnEnabled()){
				if(!success){
					logger.warn("文件删除失败："+file.getPath());
				}
			}
		}
	}

	private synchronized File getBasePathDirectory() {
		if(basePathDirectory == null){
			try {
				PathResource pathResource = new PathResource(ResourceUtils.getFile(basePath).getPath());
				basePathDirectory = new File(pathResource.getURL().getFile());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			
		}
		
		if(!basePathDirectory.exists()){
			basePathDirectory.mkdirs();
		}
		return basePathDirectory;
	}
	
//	public static void main(String[] args) throws FileNotFoundException {
//		PathResource pathResource = new PathResource(ResourceUtils.getFile("classpath:assd").getPath());
//		try {
//			System.out.println(pathResource.exists());
//			System.out.println(pathResource.getFile().getAbsolutePath());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
