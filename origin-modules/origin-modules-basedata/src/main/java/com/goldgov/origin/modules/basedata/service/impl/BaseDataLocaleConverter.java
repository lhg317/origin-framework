package com.goldgov.origin.modules.basedata.service.impl;

import com.goldgov.origin.core.discovery.rpc.ObjectConverter;
import com.goldgov.origin.modules.basedata.api.RpcBaseDataLocale;
import com.goldgov.origin.modules.basedata.service.BaseDataLocale;
import com.goldgov.origin.modules.basedata.service.ProxyBaseDataLocale;

public class BaseDataLocaleConverter implements ObjectConverter<BaseDataLocale,RpcBaseDataLocale>{

	@Override
	public RpcBaseDataLocale toRpcObject(BaseDataLocale obj) {
		return new ProxyBaseDataLocale(obj).toRpcObject();
	}

	@Override
	public BaseDataLocale fromRpcObject(RpcBaseDataLocale rpcObj) {
		return new ProxyBaseDataLocale(rpcObj);
	}

}
