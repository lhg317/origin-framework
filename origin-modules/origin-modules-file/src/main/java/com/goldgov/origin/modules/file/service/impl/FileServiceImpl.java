package com.goldgov.origin.modules.file.service.impl;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.goldgov.origin.modules.file.dao.FileDao;
import com.goldgov.origin.modules.file.service.File;
import com.goldgov.origin.modules.file.service.FileService;
import com.goldgov.origin.modules.file.service.FileStorage;

@Deprecated
public class FileServiceImpl implements FileService,ApplicationContextAware{

	private FileStorage fileStorage;
	
	@Autowired
	private FileDao fileDao;
	
	@Value("${upload.storage-type:DiskFileStorage}")
	private String storageType;

	private ApplicationContext applicationContext;
	
	
	@Override
	public void addFile(File fileMeta, InputStream content) {
		fileDao.addFile(fileMeta);
		FileStorage fileStorage = getFileStorage();
		fileStorage.saveFile(fileMeta.getFileID(), content);
	}

	@Override
	public void deleteFile(String[] ids) {
		fileDao.deleteFile(ids);
		FileStorage fileStorage = getFileStorage();
		fileStorage.deleteFile(ids);
	}

	@Override
	public File getFile(String fileID) {
		return fileDao.getFile(fileID);
	}

	@Override
	public List<File> listFile(String relationID) {
		return fileDao.listFile(relationID);
	}

	@Override
	public InputStream getFileContent(String fileID) {
		FileStorage fileStorage = getFileStorage();
		return fileStorage.loadFile(fileID);
	}

	private FileStorage getFileStorage() {
		if(fileStorage == null){
			Object bean = applicationContext.getBean(storageType);
			if(bean instanceof FileStorage){
				fileStorage = (FileStorage) bean;
			}else{
				throw new RuntimeException(storageType + "的实例不是一个有效的文件存储接口实现：" + bean.getClass());
			}
		}
		
		return fileStorage;
	}

	@Override
	public void upldateRelationID(String relationID, String[] fileIDs) {
		fileDao.upldateRelationID(relationID, fileIDs);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	}

}
