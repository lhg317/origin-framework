package com.goldgov.origin.modules.basedata.service.impl;

import com.goldgov.origin.core.discovery.rpc.ObjectConverter;
import com.goldgov.origin.modules.basedata.api.RpcBaseDataCategory;
import com.goldgov.origin.modules.basedata.service.BaseDataCategory;
import com.goldgov.origin.modules.basedata.service.ProxyBaseDataCategory;

public class BaseDataCategoryConverter implements ObjectConverter<BaseDataCategory,RpcBaseDataCategory>{

	@Override
	public RpcBaseDataCategory toRpcObject(BaseDataCategory obj) {
		return new ProxyBaseDataCategory(obj).toRpcObject();
	}

	@Override
	public BaseDataCategory fromRpcObject(RpcBaseDataCategory rpcObj) {
		return new ProxyBaseDataCategory(rpcObj);
	}

}
