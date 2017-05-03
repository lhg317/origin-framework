package com.goldgov.origin.modules.file.service.impl;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.goldgov.origin.modules.file.api.utils.FileSplitUtils;
import com.goldgov.origin.modules.file.dao.FileDao;
import com.goldgov.origin.modules.file.service.File;
import com.goldgov.origin.modules.file.service.FileFragmentService;
import com.goldgov.origin.modules.file.service.FileStorage;

@Service
public class FileFragmentServiceImpl implements FileFragmentService,ApplicationContextAware{

	private FileStorage fileStorage;
	
	@Autowired
	private FileDao fileDao;
	
	@Value("${upload.storage-type:DiskFileStorage}")
	private String storageType;

	private ApplicationContext applicationContext;
	
	private String tmpdir = System.getProperty("java.io.tmpdir");
	
	
	@Override
	public String createFileFragment(File fileMeta,int fragmentTotal) {
		fileDao.addFile(fileMeta);
//		addFileFragment(fileMeta.getFileID(),0,new byte[0]);
		return fileMeta.getFileID();
	}

	@Override
	@Transactional
	public String createFileFragment(File fileMeta,int fragmentTotal, byte[] bytes) {
		fileDao.addFile(fileMeta);
		addFileFragment(fileMeta.getFileID(),0,bytes);
		return fileMeta.getFileID();
	}

	@Override
	public void addFileFragment(String fileID,int fragment, byte[] bytes) {
		try {
			java.io.File tempFile = new java.io.File(tmpdir+fileID + "."+fragment);
			if(bytes.length ==0){
				return;
			}
			FileCopyUtils.copy(new ByteArrayInputStream(bytes), new FileOutputStream(tempFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void completeFileFragment(String fileID,int fragmentTotal) {
		
		java.io.File completeFile = null;
		FileOutputStream fos = null;
		try {
			completeFile = new java.io.File(tmpdir + fileID);
			fos = new FileOutputStream(completeFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.io.File[] allFragFile = new java.io.File[fragmentTotal];
		
		FileInputStream fis = null;
		byte[] buffer = new byte[4096];
		int count = -1;
		try {
			for (int i = 0; i < fragmentTotal; i++) {
				java.io.File fragFile = new java.io.File(tmpdir + fileID + "." + i); 
				if(fragFile.exists()){
					fis = new FileInputStream(fragFile);
					while((count = fis.read(buffer)) != -1){
						fos.write(buffer, 0, count);
					}
					fis.close();
					allFragFile[i] = fragFile;
				}else{
					throw new RuntimeException(fragFile.getName());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fos.close();
				if(fis != null)fis.close();
			} catch (IOException e) {}
			for (java.io.File f : allFragFile) {
				f.delete();
			}
		}
		
		FileStorage fileStorage = getFileStorage();
		try {
			fileStorage.saveFile(fileID, new FileInputStream(completeFile));
			completeFile.delete();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void addFile(File fileMeta, InputStream content) {
		fileDao.addFile(fileMeta);
		getFileStorage().saveFile(fileMeta.getFileID(), content);
	}

	@Override
	public void deleteFile(String[] ids) {
		fileDao.deleteFile(ids);
		getFileStorage().deleteFile(ids);
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

	@Override
	public void upldateRelationID(String relationID, String[] fileIDs) {
		fileDao.upldateRelationID(relationID, fileIDs);
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
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public byte[] getFileFragmentContent(String fileID,long startIndex) {
		InputStream fileContent = getFileContent(fileID);
		try {
			System.out.println(fileContent.skip(startIndex));
			byte[] fileSplit = FileSplitUtils.fileSplit(fileContent,1048576);
			if(fileSplit.length > 0){
				return fileSplit;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fileContent.close();
			} catch (IOException e) {}
		}
		return null;
	}

}
