package com.goldgov.origin.modules.basedata.service.impl;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.goldgov.origin.modules.basedata.dao.BaseDataDao;
import com.goldgov.origin.modules.basedata.event.DataModificationEvent;
import com.goldgov.origin.modules.basedata.event.DataModificationEvent.EventType;
import com.goldgov.origin.modules.basedata.service.BaseData;
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
	public void addData(BaseData data) {
		baseDao.addData(data);
		applicationContext.publishEvent(new DataModificationEvent(data,EventType.ADD));
	}

	@Override
	public void updateData(BaseData data) {
		baseDao.updateData(data);
		applicationContext.publishEvent(new DataModificationEvent(data,EventType.UPDATE));
	}

	@Override
	public void deleteData(String[] dataID) {
		baseDao.deleteData(dataID);
		applicationContext.publishEvent(new DataModificationEvent(dataID,EventType.DELETE));
	}

	@Override
	public List<BaseData> listData(String localeCode, String dataName) {
		return baseDao.listData(localeCode, dataName);
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

}
