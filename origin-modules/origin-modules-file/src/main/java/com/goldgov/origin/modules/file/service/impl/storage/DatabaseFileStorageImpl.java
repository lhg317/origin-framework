package com.goldgov.origin.modules.file.service.impl.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Array;
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
	
	@Override
	public void saveFile(String fileID, InputStream fileStream) {
		// TODO Auto-generated method stub
		if(saveScript == null || "".equals(saveScript)){
			saveScript = "SELECT t.CONTENT from FILE_CONTENT t WHERE t.FILE_ID = ? for update ";
		}
		jdbcTemplate.execute(new FileSaveCallback(fileID,fileStream));
	}

	@Override
	public InputStream loadFile(String fileID) {
		// TODO Auto-generated method stub
		if(findScript == null || "".equals(deleteScript)){
			findScript = "SELECT t.CONTENT from FILE_CONTENT t WHERE t.FILE_ID = ? ";
		}
		File tmpFile = jdbcTemplate.execute(new FileFindCallback(fileID));
		try {
			return new FileInputStream(tmpFile);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("文件不存在，fileID="+fileID,e);
		}
	}

	@Override
	public void deleteFile(String[] fileIDs) {
		// TODO Auto-generated method stub
		if(deleteScript == null || "".equals(deleteScript)){
			deleteScript = "DELETE FROM FILE_CONTENT t WHERE t.FILE_ID IN (?)";
		}
		jdbcTemplate.execute(new FileDeleteCallback(fileIDs));
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
				java.sql.Blob blob = resultSet.getBlob("CONTENT");
				outputStream = blob.setBinaryStream(1);
				try {
					FileCopyUtils.copy(fileStream, outputStream);
				} catch (IOException e) {
					throw new RuntimeException("upload File Failed. fileID= " + fileID, e);
				}finally{
					resultSet.close();
					prepareStatement.close();
				}
			}
			return null;
		}
		
	}
	
	class FileFindCallback implements ConnectionCallback<File>{

		private String fileID;

		public FileFindCallback(String fileID){
			this.fileID = fileID;
		}
		
		@Override
		public File doInConnection(Connection con) throws SQLException, DataAccessException {
			PreparedStatement prepareStatement = con.prepareStatement(findScript);
			prepareStatement.setString(1, fileID);
			ResultSet resultSet = prepareStatement.executeQuery();
			File tempFile = null;
			try {
				tempFile = File.createTempFile("download_", ".tmp");
			} catch (IOException e) {
				throw new RuntimeException("创建下载用的临时文件时发生IO错误",e);
			}
			if(resultSet.next()) {
				//存储在临时文件，不要频繁访问数据库
				InputStream binaryStream = resultSet.getBinaryStream(1);
				try {
					FileCopyUtils.copy(binaryStream, new FileOutputStream(tempFile));
				} catch (Exception e) {
					throw new RuntimeException("创建下载用临时文件时发生错误：" + tempFile.getPath(),e);
				}
			}
			prepareStatement.close();
			return tempFile;
		}
		
	}
	
	class FileDeleteCallback implements ConnectionCallback<Integer>{

		private String[] fileIDs;

		public FileDeleteCallback(String[] fileIDs){
			this.fileIDs = fileIDs;
		}
		
		@Override
		public Integer doInConnection(Connection con) throws SQLException, DataAccessException {
			PreparedStatement prepareStatement = con.prepareStatement(deleteScript);
			Array fileArray = con.createArrayOf("VARCHAR", fileIDs);
			prepareStatement.setArray(1, fileArray);
			int result = prepareStatement.executeUpdate();
			prepareStatement.close();
			return result;
		}
		
	}
}
