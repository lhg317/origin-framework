package com.goldgov.origin.modules.basedata.service.impl;

import java.util.List;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldgov.origin.core.discovery.rpc.ObjectConverter.Utils;
import com.goldgov.origin.core.discovery.rpc.ResultSetUtils;
import com.goldgov.origin.core.discovery.rpc.RpcService;
import com.goldgov.origin.modules.basedata.api.RpcBaseData;
import com.goldgov.origin.modules.basedata.api.RpcBaseDataCategory;
import com.goldgov.origin.modules.basedata.api.RpcBaseDataLocale;
import com.goldgov.origin.modules.basedata.api.RpcBaseDataService;
import com.goldgov.origin.modules.basedata.service.BaseData;
import com.goldgov.origin.modules.basedata.service.BaseDataCategory;
import com.goldgov.origin.modules.basedata.service.BaseDataLocale;
import com.goldgov.origin.modules.basedata.service.BaseDataService;
import com.goldgov.origin.modules.basedata.service.ProxyBaseData;
import com.goldgov.origin.modules.basedata.service.ProxyBaseDataCategory;
import com.goldgov.origin.modules.basedata.service.ProxyBaseDataLocale;

@RpcService
public class RpcBaseDataServiceImpl implements RpcBaseDataService.Iface{

	@Autowired
	private BaseDataService baseDataService;
	
	private BaseDataConverter baseDateConverter = new BaseDataConverter();
	private BaseDataCategoryConverter baseDataCategoryConverter = new BaseDataCategoryConverter();
	private BaseDataLocaleConverter baseDateLocaleConverter = new BaseDataLocaleConverter();
	
	@Override
	public String addLocale(RpcBaseDataLocale locale) throws TException {
		baseDataService.addLocale(baseDateLocaleConverter.fromRpcObject(locale));
		return locale.getLocaleID();
	}

	@Override
	public void updateLocale(RpcBaseDataLocale locale) throws TException {
		baseDataService.updateLocale(baseDateLocaleConverter.fromRpcObject(locale));
	}

	@Override
	public void deleteLocale(String localeID) throws TException {
		baseDataService.deleteLocale(localeID);
	}

	@Override
	public List<RpcBaseDataLocale> listLocale() throws TException {
		List<BaseDataLocale> rpcLocaleList = baseDataService.listLocale();
		List<RpcBaseDataLocale> localeList = ResultSetUtils.convertToRpc(rpcLocaleList, baseDateLocaleConverter);
		return localeList;
	}

	@Override
	public String addData(RpcBaseData data) throws TException {
		baseDataService.addData(baseDateConverter.fromRpcObject(data));
		return data.getDataID();
	}

	@Override
	public void updateData(RpcBaseData data) throws TException {
		baseDataService.updateData(baseDateConverter.fromRpcObject(data));
	}

	@Override
	public void deleteData(List<String> dataID) throws TException {
		baseDataService.deleteData(Utils.listToArray(dataID));
	}

	@Override
	public List<RpcBaseData> listData(String localeCode, String dataName) throws TException {
		List<BaseData> listData = baseDataService.listData(localeCode, dataName);
		List<RpcBaseData> listRpcData = ResultSetUtils.convertToRpc(listData, baseDateConverter);
		return listRpcData;
	}

	@Override
	public RpcBaseDataLocale getLocale(String localeID) throws TException {
		BaseDataLocale locale = baseDataService.getLocale(localeID);
		return new ProxyBaseDataLocale(locale).toRpcObject();
	}

	@Override
	public RpcBaseData getData(String dataID) throws TException {
		BaseData data = baseDataService.getData(dataID);
		return new ProxyBaseData(data).toRpcObject();
	}

	@Override
	public String addCategory(RpcBaseDataCategory category) throws TException {
		baseDataService.addCategory(baseDataCategoryConverter.fromRpcObject(category));
		return category.getCategoryID();
	}

	@Override
	public RpcBaseDataCategory getCategory(String categoryID) throws TException {
		BaseDataCategory category = baseDataService.getCategory(categoryID);
		return new ProxyBaseDataCategory(category).toRpcObject();
	}

	@Override
	public void updateCategory(RpcBaseDataCategory category) throws TException {
		baseDataService.updateCategory(baseDataCategoryConverter.fromRpcObject(category));
	}

	@Override
	public void deleteCategory(String categoryID) throws TException {
		baseDataService.deleteCategory(categoryID);
	}

	@Override
	public List<RpcBaseDataCategory> listCategory() throws TException {
		List<BaseDataCategory> listCategory = baseDataService.listCategory();
		return ResultSetUtils.convertToRpc(listCategory, baseDataCategoryConverter);
	}

	@Override
	public List<RpcBaseData> listDataByParent(String localeCode, String categoryCode, String parentID)
			throws TException {
		List<BaseData> listData = baseDataService.listData(localeCode, categoryCode, parentID);
		List<RpcBaseData> listRpcData = ResultSetUtils.convertToRpc(listData, baseDateConverter);
		
		return listRpcData;
	}

}
