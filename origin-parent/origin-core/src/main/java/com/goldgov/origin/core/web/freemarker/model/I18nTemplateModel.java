package com.goldgov.origin.core.web.freemarker.model;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.springframework.util.Assert;

import com.goldgov.origin.core.web.interceptor.handler.impl.LocaleChangeHandler.MessagesHolder;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class I18nTemplateModel implements TemplateDirectiveModel {

	private DefaultObjectWrapper objectWrapper = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25).build();
	
	@Override
	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
//		TemplateModel i8nContext = env.getVariable(AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE);
//		RequestContext requestContext = (RequestContext)objectWrapper.unwrap(i8nContext);
//		requestContext.changeLocale(locale);
		Object code = params.get("code");
		Assert.notNull(code,"@i18n code属性不能为空");
		
		Object args = params.get("args");
		
//		Object defaultMessage = params.get("defaultMessage");
		
		String message = null;
		if(args != null){
			String[] argArray = args.toString().split("[|]");
			message = MessagesHolder.getMessage((String)objectWrapper.unwrap((TemplateModel) code),argArray);
		}else{
			if(code.toString().startsWith("i18n:")){
				message = MessagesHolder.getMessageByPrefix((String)objectWrapper.unwrap((TemplateModel) code));
			}else{
				message = MessagesHolder.getMessage((String)objectWrapper.unwrap((TemplateModel) code));
			}
		}
		
		Object suffix = params.get("suffix");
		if(suffix != null){
			message = message + MessagesHolder.getMessage((String)objectWrapper.unwrap((TemplateModel) suffix));
		}
		
//		int num = 0;
//		List<Object> arrayList = new ArrayList<Object>();
//		while(true){
//			Object value = params.get("args"+num);
//			if(value == null){
//				break;
//			}
//			arrayList.add(value);
//			num++;
//		}
//		if(arrayList.size() > 0){
//			message = requestContext.getMessage((String)objectWrapper.unwrap((TemplateModel) code),arrayList.toArray());
//		}else{
//			message = requestContext.getMessage((String)objectWrapper.unwrap((TemplateModel) code));
//		}
		
		Writer out = env.getOut();
		if(body != null){
			env.setVariable("i18n_"+code, objectWrapper.wrap(message));
			body.render(out);
		}else{
			out.write(message);
		}
	}


}
