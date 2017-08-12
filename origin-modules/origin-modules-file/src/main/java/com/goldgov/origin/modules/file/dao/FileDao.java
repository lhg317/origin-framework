package com.goldgov.origin.modules.file.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldgov.origin.core.dao.Mapper;
import com.goldgov.origin.modules.file.service.File;

@Mapper
public interface FileDao {

	void addFile(File fileMeta);
	
	void deleteFile(@Param("ids") String[] ids);
	
	void deleteFileByGroupID(@Param("groupID") String id);
	
	File getFile(@Param("fileID") String fileID);
	
	List<File> listFile(@Param("groupID") String groupID);
	
	void updateGroupID(@Param("groupID") String groupID, @Param("ids") String[] fileIDs);
}
