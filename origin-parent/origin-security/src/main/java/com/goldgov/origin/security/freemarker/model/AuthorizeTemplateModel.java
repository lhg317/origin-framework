package com.goldgov.origin.security.freemarker.model;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import com.goldgov.origin.core.cache.CacheHolder;
import com.goldgov.origin.security.UserDelegate;
import com.goldgov.origin.security.UserHolder;
import com.goldgov.origin.security.resource.ResourceConstants;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class AuthorizeTemplateModel implements TemplateDirectiveModel{

	private BeansWrapper beansWrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_25).build();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		Object resourceCode = params.get("code");
		UserDelegate user = UserHolder.getUser();
		
		if(user == null){
			return;
		}
		
		env.setVariable("userToken", beansWrapper.wrap(user));
		Writer out = env.getOut();
		
		if(resourceCode == null){
			body.render(out);
		}else{
			Map<String,List<String>> roleResourceMapping = (Map<String, List<String>>) CacheHolder.get(ResourceConstants.CACHE_CODE_ROLE_RESOURCE_MAPPING);
			List<String> roleCodeList = roleResourceMapping.get(resourceCode.toString());
			
			if(roleCodeList == null){
				return;
			}
			
			for (String roleCode : user.getRoles()) {
				if(roleCodeList.contains(roleCode)){
					body.render(out);
					return;
				}
			}
		}
	}
}
