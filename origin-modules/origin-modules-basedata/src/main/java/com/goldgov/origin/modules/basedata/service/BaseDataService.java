package com.goldgov.origin.modules.basedata.service;

import java.util.List;

public interface BaseDataService {
	
	public static final String CACHE_CODE_BASE_DATA = "CACHE_CODE_BASE_DATA";
	
	void addLocale(BaseDataLocale locale);
	
	BaseDataLocale getLocale(String localeID);
	
	void updateLocale(BaseDataLocale locale);
	
	void deleteLocale(String localeID);
	
	List<BaseDataLocale> listLocale();
	
	void addCategory(BaseDataCategory category);
	
	BaseDataCategory getCategory(String categoryID);
	
	void updateCategory(BaseDataCategory category);
	
	void deleteCategory(String categoryID);
	
	List<BaseDataCategory> listCategory();
	
	void addData(BaseData data);
	
	BaseData getData(String dataID);
	
	void updateData(BaseData data);
	
	void deleteData(String[] dataID);
	
	/**
	 * 根据语言环境编码和分类编码查询其下的所有基础数据（不分层级，平级返回），
	 * 此查询首先会检查缓存，如果缓存中不存在，则去数据库中查询。
	 * @param localeCode
	 * @param categoryCode
	 * @return
	 */
	List<BaseData> listData(String localeCode,String categoryCode);
	
	List<BaseData> listData(String localeCode,String categoryCode,String parentID);
	
}
