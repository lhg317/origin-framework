package com.goldgov.origin.modules.basedata.service;

import com.goldgov.origin.core.discovery.rpc.ProxyObject;
import com.goldgov.origin.modules.basedata.api.RpcBaseDataLocale;

public class ProxyBaseDataLocale extends BaseDataLocale implements ProxyObject<RpcBaseDataLocale>{

	private RpcBaseDataLocale rpcBaseDataLocale;
	
	public ProxyBaseDataLocale(){
		rpcBaseDataLocale = new RpcBaseDataLocale();
	}
	
	public ProxyBaseDataLocale(RpcBaseDataLocale rpcBaseDataLocale){
		this.rpcBaseDataLocale = rpcBaseDataLocale;
	}
	
	public ProxyBaseDataLocale(BaseDataLocale baseDataLocale){
		rpcBaseDataLocale = new RpcBaseDataLocale();
		rpcBaseDataLocale.setLocaleID(baseDataLocale.getLocaleID());
		rpcBaseDataLocale.setLocaleName(baseDataLocale.getLocaleName());
		rpcBaseDataLocale.setLocaleCode(baseDataLocale.getLocaleCode());
	}
	
	public String getLocaleID() {
		return rpcBaseDataLocale.getLocaleID();
	}
	public void setLocaleID(String localeID) {
		rpcBaseDataLocale.setLocaleID(localeID);
	}
	public String getLocaleCode() {
		return rpcBaseDataLocale.getLocaleCode();
	}
	public void setLocaleCode(String localeCode) {
		rpcBaseDataLocale.setLocaleCode(localeCode);
	}
	public String getLocaleName() {
		return rpcBaseDataLocale.getLocaleName();
	}
	public void setLocaleName(String localeName) {
		rpcBaseDataLocale.setLocaleName(localeName);
	}
	
	public RpcBaseDataLocale toRpcObject(){
		return rpcBaseDataLocale;
	}
}
