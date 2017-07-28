package com.goldgov.origin.security.freemarker.model;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
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
		Object resourceCodeArgs = params.get("code");
		Object roleCodeArg = params.get("role");
		UserDelegate user = UserHolder.getUser();
		
		if(user == null){
			return;
		}
		
		env.setVariable("userToken", beansWrapper.wrap(user));
		Writer out = env.getOut();
		
		if(resourceCodeArgs == null && roleCodeArg == null){
			body.render(out);
		}else{
			Map<String,List<String>> roleResourceMapping = (Map<String, List<String>>) CacheHolder.get(ResourceConstants.CACHE_CODE_ROLE_RESOURCE_MAPPING);
			List<String> roleCodeList = null;
			if(resourceCodeArgs != null){
				if(roleResourceMapping == null){
					throw new RuntimeException("资源未初始化："
							+ "方法1 - 使用本标签之前通过CacheHolder.put方法添加一个名为ResourceConstants.CACHE_CODE_ROLE_RESOURCE_MAPPING，值为Map对象，k-角色编码，并将角色分配给该用户，v-资源编码（例如：" + resourceCodeArgs + "）；"
							+ "方法2 - 加入origin-security模块，继承BaseAccessDecisionManager类，实现roleResourceMap方法。");
				}
				roleCodeList = roleResourceMapping.get(resourceCodeArgs.toString());
			}
			
			if(roleCodeArg != null){
				String[] roles = roleCodeArg.toString().split("[,;]");
				roleCodeList = roleCodeList == null ? new ArrayList<String>():roleCodeList;
				roleCodeList.addAll(Arrays.asList(roles));
			}
			
			
			if(roleCodeList == null){
				return;
			}
			
			for (String rCode : user.getRoles()) {
				if(roleCodeList.contains(rCode)){
					body.render(out);
					return;
				}
			}
		}
	}
}
