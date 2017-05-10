package com.goldgov.origin.modules.file.service.impl.storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.goldgov.origin.modules.file.service.FileStorage;

@Component("DatabaseFileStorage")
public class DatabaseFileStorageImpl implements FileStorage {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
//	private String[] beforeScripts;
	private String saveScript;
	private String findScript;
	private String deleteScript;
//	private String[] afterScripts;
	
	private String blobFieldName = "CONTENT";
	
	@Override
	public void saveFile(String fileID, InputStream fileStream) {
		// TODO Auto-generated method stub
		if(saveScript == null || "".equals(saveScript)){
			saveScript = "SELECT t." + blobFieldName + " from FILE_CONTENT t WHERE t.FILE_ID = ? for update ";
		}
		jdbcTemplate.execute(new FileSaveCallback(fileID,fileStream));
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

	public String getFindScript() {
		return findScript;
	}

	public void setFindScript(String findScript) {
		this.findScript = findScript;
	}

	public String getSaveScript() {
		return saveScript;
	}

	public void setSaveScript(String saveScript) {
		this.saveScript = saveScript;
	}

	public String getDeleteScript() {
		return deleteScript;
	}

	public void setDeleteScript(String deleteScript) {
		this.deleteScript = deleteScript;
	}

	public String getBlobFieldName() {
		return blobFieldName;
	}

	public void setBlobFieldName(String blobFieldName) {
		this.blobFieldName = blobFieldName;
	}


	class FileSaveCallback implements ConnectionCallback<Void>{

		private String fileID;
		private InputStream fileStream;

		public FileSaveCallback(String fileID,InputStream fileStream){
			this.fileID = fileID;
			this.fileStream = fileStream;}
		
		@Override
		public Void doInConnection(Connection con) throws SQLException, DataAccessException {
			PreparedStatement prepareStatement = con.prepareStatement(saveScript);
			prepareStatement.setString(1, fileID);
			ResultSet resultSet = prepareStatement.executeQuery();
			if(resultSet.next()) {
				OutputStream outputStream = null;
				java.sql.Blob blob = resultSet.getBlob(blobFieldName);
				outputStream = blob.setBinaryStream(1);
				try {
					FileCopyUtils.copy(fileStream, outputStream);
				} catch (IOException e) {
					throw new RuntimeException("upload File Failed.fileID= " + fileID, e);
				}finally{
					resultSet.close();
					prepareStatement.close();
				}
			}
			return null;
		}
		
	}
}
