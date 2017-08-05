package com.goldgov.origin.modules.basedata.service;

import java.util.List;

public interface BaseDataService {

	void addLocale(BaseDataLocale locale);
	
	BaseDataLocale getLocale(String localeID);
	
	void updateLocale(BaseDataLocale locale);
	
	void deleteLocale(String localeID);
	
	List<BaseDataLocale> listLocale();
	
	void addData(BaseData data);
	
	BaseData getData(String dataID);
	
	void updateData(BaseData data);
	
	void deleteData(String[] dataID);
	
	List<BaseData> listData(String localeCode,String dataName);
	
}
