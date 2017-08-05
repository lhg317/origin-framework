package com.goldgov.origin.modules.basedata.service.impl;

import com.goldgov.origin.core.discovery.rpc.ObjectConverter;
import com.goldgov.origin.modules.basedata.api.RpcBaseData;
import com.goldgov.origin.modules.basedata.service.BaseData;
import com.goldgov.origin.modules.basedata.service.ProxyBaseData;

public class BaseDataConverter implements ObjectConverter<BaseData,RpcBaseData>{

	@Override
	public RpcBaseData toRpcObject(BaseData obj) {
		return new ProxyBaseData(obj).toRpcObject();
	}

	@Override
	public BaseData fromRpcObject(RpcBaseData rpcObj) {
		return new ProxyBaseData(rpcObj);
	}

}
