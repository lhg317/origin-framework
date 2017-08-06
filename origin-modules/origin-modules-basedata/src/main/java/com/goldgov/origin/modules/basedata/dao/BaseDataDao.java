package com.goldgov.origin.modules.basedata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldgov.origin.core.dao.Mapper;
import com.goldgov.origin.modules.basedata.service.BaseData;
import com.goldgov.origin.modules.basedata.service.BaseDataCategory;
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
	
	
	void addCategory(BaseDataCategory category);
	
	BaseDataCategory getCategory(String categoryID);
	
	void updateCategory(BaseDataCategory category);
	
	void deleteCategory(String categoryID);
	
	List<BaseDataCategory> listCategory();
	
	
	Integer getMaxNodeValue();
	
	String getNodePath(@Param("dataID")String dataID);
	
	void addData(BaseData data);
	
	void addDataValue(@Param("dataID")String dataID,@Param("localeID")String localeID,@Param("dataValue")String value,@Param("orderNum")Integer orderNum);
	
	BaseData getData(String dataID);
	
	void updateData(BaseData data);
	
	void updateDataValue(@Param("dataID")String dataID,@Param("localeID")String localeID,@Param("dataValue")String value,@Param("orderNum")Integer orderNum);
	
	void deleteData(@Param("ids") String[] dataID);
	
	List<BaseData> listData(@Param("localeCode") String localeCode,@Param("categoryCode") String categoryCode);
	
	List<BaseData> listDataByParent(@Param("localeCode") String localeCode,@Param("categoryCode") String categoryCode,@Param("parentID")String parentID);
}
