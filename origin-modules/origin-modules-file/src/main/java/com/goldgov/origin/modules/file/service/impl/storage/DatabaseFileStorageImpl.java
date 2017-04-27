package com.goldgov.origin.modules.file.service.impl.storage;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.goldgov.origin.modules.file.service.FileStorage;

@Component("DatabaseFileStorage")
public class DatabaseFileStorageImpl implements FileStorage {

	@Override
	public void saveFile(String fileID, InputStream fileStream) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InputStream loadFile(String fileID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFile(String[] fileID) {
		// TODO Auto-generated method stub
		
	}

}
