package com.goldgov.origin.modules.basedata.service;

import java.util.List;

public interface BaseDataService {
	
	public static final String CACHE_CODE_BASE_DATA = "CACHE_CODE_BASE_DATA";
	
	/**
	 * 添加基础数据语言环境
	 * @param locale
	 */
	void addLocale(BaseDataLocale locale);
	
	BaseDataLocale getLocale(String localeID);
	
	void updateLocale(BaseDataLocale locale);
	
	void deleteLocale(String localeID);
	
	List<BaseDataLocale> listLocale();
	
	/**
	 * 添加基础数据分类
	 * @param category
	 */
	void addCategory(BaseDataCategory category);
	
	BaseDataCategory getCategory(String categoryID);
	
	void updateCategory(BaseDataCategory category);
	
	void deleteCategory(String categoryID);
	
	List<BaseDataCategory> listCategory();
	
	/**
	 * 添加基础数据
	 * @param data
	 */
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
	 * @see {@link com.goldgov.origin.modules.basedata.service.TreeData TreeData}
	 * @see {@link com.goldgov.origin.modules.basedata.RefreshBaseDataCacheListener RefreshBaseDataCacheListener}
	 */
	List<BaseData> listData(String localeCode,String categoryCode);
	
	/**
	 * 查询指定的父级数据ID，查询子集基础数据，本方法只查询子集一级，不会继续向下查询。
	 * @param localeCode 语言环境编码
	 * @param categoryCode 基础数据分类编码
	 * @param parentID 基础数据父级ID
	 * @return 子集基础数据对象（只有子集当前级别数据，不包含子级以下的基础数据），如果没有符合条件的返回空的集合。
	 */
	List<BaseData> listData(String localeCode,String categoryCode,String parentID);
	
}
