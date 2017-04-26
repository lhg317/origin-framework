package com.goldgov.origin.modules.file.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldgov.origin.modules.file.dao.FileDao;
import com.goldgov.origin.modules.file.service.File;
import com.goldgov.origin.modules.file.service.FileService;
import com.goldgov.origin.modules.file.service.FileStorage;
import com.goldgov.origin.modules.file.service.UploadConfig;

@Service
public class FileServiceImpl implements FileService{

	@Autowired(required=false)
	private Map<String,UploadConfig> uploadConfigMap;
	
	@Autowired
	private Map<String,FileStorage> fileStorageMap;
	
	@Autowired
	private FileDao fileDao;
	
	@Override
	public void addFile(String serviceName,File fileMeta, InputStream content) {
//		fileDao.addFile(fileMeta);
		FileStorage fileStorage = getFileStorage("DiskFileStorage");
		fileStorage.saveFile(fileMeta.getFileID(), content);
	}

	@Override
	public void deleteFile(String serviceName,String[] ids) {
		fileDao.deleteFile(ids);
		FileStorage fileStorage = getFileStorage(serviceName);
		fileStorage.deleteFile(ids);
	}

	@Override
	public File getFile(String serviceName,String fileID) {
		
		return fileDao.getFile(fileID);
	}

	@Override
	public List<File> listFile(String serviceName,String relationID) {
		
		return fileDao.listFile(relationID);
	}

	@Override
	public InputStream getFileContent(String serviceName,String fileID) {
		FileStorage fileStorage = fileStorageMap.get(serviceName);
		return fileStorage.loadFile(fileID);
	}

	private FileStorage getFileStorage(String serviceName) {
		FileStorage fileStorage = fileStorageMap.get(serviceName);
		if(fileStorage == null){
			throw new RuntimeException("文件存储器未配置：" + serviceName);
		}
		return fileStorage;
	}
}
