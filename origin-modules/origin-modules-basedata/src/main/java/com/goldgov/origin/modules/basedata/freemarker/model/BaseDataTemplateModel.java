package com.goldgov.origin.modules.basedata.freemarker.model;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;

import com.goldgov.origin.core.utils.SpringBeanUtils;
import com.goldgov.origin.modules.basedata.api.RpcBaseData;
import com.goldgov.origin.modules.basedata.api.RpcBaseDataService.Iface;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class BaseDataTemplateModel implements TemplateDirectiveModel{

	private ThreadLocal<Environment> envThreadLocal = new ThreadLocal<Environment>();
	
	private List<RpcBaseData> listData = null;
	private Map<String,String> dataCache = new HashMap<>();
	
	private Iface rpcBaseDataService;

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		boolean sameRequest = true;
		if(envThreadLocal.get() == null || !envThreadLocal.get().equals(env)){
			envThreadLocal.set(env);
			sameRequest = false;
		}
		Object localeCode = params.get("locale");
		Object categoryCode = params.get("category");
		Object dataName = params.get("name");
		
		Assert.notNull(categoryCode,"基础数据分类编码不能为null");
		Assert.notNull(dataName,"基础数据名不能为null");
		
		if(localeCode == null){
			localeCode = LocaleContextHolder.getLocale().toString();
		}
		
		if(!sameRequest || listData == null){
			if(rpcBaseDataService == null){
				rpcBaseDataService = SpringBeanUtils.getBean("rpcBaseDataService.Client");
			}
			try {
				listData = rpcBaseDataService.listData(localeCode.toString(), categoryCode.toString());
			} catch (Exception e) {
				throw new RuntimeException("获取基础数据时发生错误，localeCode:" + localeCode+",categoryCode:" + categoryCode,e);
			}
			for (RpcBaseData baseData : listData) {
				dataCache.put(baseData.getDataName(), baseData.getDataValue());
			}
		}
		Writer out = env.getOut();
		//FIXME NULL POINTER
		out.write(dataCache.get(dataName.toString()));
		
	}
	
}
