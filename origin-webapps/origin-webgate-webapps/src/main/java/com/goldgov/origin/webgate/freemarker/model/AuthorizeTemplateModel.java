package com.goldgov.origin.webgate.freemarker.model;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import com.goldgov.origin.core.cache.CacheHolder;
import com.goldgov.origin.modules.role.RoleConstants;
import com.goldgov.origin.security.UserDelegate;
import com.goldgov.origin.security.UserHolder;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class AuthorizeTemplateModel implements TemplateDirectiveModel{

//	private DefaultObjectWrapper objectWrapper = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25).build();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		Object resourceCode = params.get("code");
		UserDelegate user = UserHolder.getUser();
		
		Map<String,List<String>> roleResourceMapping = (Map<String, List<String>>) CacheHolder.get(RoleConstants.CACHE_CODE_ROLE_RESOURCE_MAPPING);
		List<String> roleCodeList = roleResourceMapping.get(resourceCode.toString());
		
		Writer out = env.getOut();
		
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
