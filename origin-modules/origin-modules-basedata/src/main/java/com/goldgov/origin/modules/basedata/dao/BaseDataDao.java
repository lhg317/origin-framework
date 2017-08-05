package com.goldgov.origin.modules.basedata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldgov.origin.core.dao.Mapper;
import com.goldgov.origin.modules.basedata.service.BaseData;
import com.goldgov.origin.modules.basedata.service.BaseDataLocale;

/**
 * 
 * @author LiuHG
 * @version 1.0
 */
@Mapper
public interface BaseDataDao {

	void addLocale(BaseDataLocale locale);
	
	BaseDataLocale getLocale(String localeID);
	
	void updateLocale(BaseDataLocale locale);
	
	void deleteLocale(@Param("id") String localeID);
	
	List<BaseDataLocale> listLocale();
	
	void addData(BaseData data);
	
	BaseData getData(String dataID);
	
	void updateData(BaseData data);
	
	void deleteData(@Param("ids") String[] dataID);
	
	List<BaseData> listData(@Param("localeCode") String localeCode,@Param("dataName") String dataName);
}
