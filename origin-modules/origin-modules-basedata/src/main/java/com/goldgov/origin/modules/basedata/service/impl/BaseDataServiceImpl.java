package com.goldgov.origin.modules.basedata.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goldgov.origin.core.cache.CacheHolder;
import com.goldgov.origin.core.utils.DataPathUtils;
import com.goldgov.origin.modules.basedata.dao.BaseDataDao;
import com.goldgov.origin.modules.basedata.event.BaseDataEvent;
import com.goldgov.origin.modules.basedata.event.BaseDataEvent.EventType;
import com.goldgov.origin.modules.basedata.service.BaseData;
import com.goldgov.origin.modules.basedata.service.BaseDataCategory;
import com.goldgov.origin.modules.basedata.service.BaseDataLocale;
import com.goldgov.origin.modules.basedata.service.BaseDataService;

@Service
public class BaseDataServiceImpl implements BaseDataService,ApplicationContextAware{

	@Autowired
	private BaseDataDao baseDao;
	
	private ApplicationContext applicationContext;
	
	@Override
	public void addLocale(BaseDataLocale locale) {
		baseDao.addLocale(locale);
	}

	@Override
	public void updateLocale(BaseDataLocale locale) {
		baseDao.updateLocale(locale);
	}

	@Override
	public void deleteLocale(String localeID) {
		baseDao.deleteLocale(localeID);
	}

	@Override
	public List<BaseDataLocale> listLocale() {
		return baseDao.listLocale();
	}

	@Override
	@Transactional
	public void addData(BaseData data) {
		
		String basePath = null;
		if(data.getParentData() != null && data.getParentData().getDataID() != null){
			basePath = baseDao.getNodePath(data.getParentData().getDataID());
		}
		
		Integer maxNodeValue = 0;
		synchronized(this){
			maxNodeValue = baseDao.getMaxNodeValue();
		}
		
		maxNodeValue = maxNodeValue == null?1:maxNodeValue + 1;
		
		data.setNodeValue(maxNodeValue);
		basePath = basePath == null ? "" : basePath;
		data.setNodePath(DataPathUtils.appendPath(basePath, "" + maxNodeValue));
		baseDao.addData(data);
		baseDao.addDataValue(data.getDataID(), data.getDataLocale().getLocaleID(), data.getDataValue(), data.getOrderNum());
		applicationContext.publishEvent(new BaseDataEvent(data.getDataID(),EventType.ADD));
	}

	@Override
	@Transactional
	public void updateData(BaseData data) {
		baseDao.updateData(data);
		baseDao.updateDataValue(data.getDataID(), data.getDataCategory().getCategoryID(), data.getDataValue(), data.getOrderNum());
		applicationContext.publishEvent(new BaseDataEvent(data.getDataID(),EventType.UPDATE));
	}

	@Override
	public void deleteData(String[] dataID) {
		if(dataID.length > 0){
			applicationContext.publishEvent(new BaseDataEvent(dataID[0],EventType.PRE_DELETE));
		}
		baseDao.deleteData(dataID);
	}

	@Override
	public List<BaseData> listData(String localeCode, String dataName,String parentID) {
		return baseDao.listDataByParent(localeCode, dataName,parentID);
	}

	@Override
	public BaseDataLocale getLocale(String localeID) {
		return baseDao.getLocale(localeID);
	}

	@Override
	public BaseData getData(String dataID) {
		return baseDao.getData(dataID);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void addCategory(BaseDataCategory category) {
		baseDao.addCategory(category);
	}

	@Override
	public BaseDataCategory getCategory(String categoryID) {
		return baseDao.getCategory(categoryID);
	}

	@Override
	public void updateCategory(BaseDataCategory category) {
		baseDao.updateCategory(category);
	}

	@Override
	public void deleteCategory(String categoryID) {
		baseDao.deleteCategory(categoryID);
	}

	@Override
	public List<BaseDataCategory> listCategory() {
		return baseDao.listCategory();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BaseData> listData(String localeCode, String categoryCode) {
		Map<String,List<BaseData>> cache = (Map<String,List<BaseData>>) CacheHolder.get(BaseDataService.CACHE_CODE_BASE_DATA);
		List<BaseData> listData = null;
		if(cache != null){
			listData = cache.get(categoryCode);
		}
		if(listData == null){
			listData = baseDao.listData(localeCode, categoryCode);
			applicationContext.publishEvent(new BaseDataEvent(listData,EventType.LIST));
		}
		return listData;
	}

}
