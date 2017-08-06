package com.goldgov.origin.core.web.freemarker.model;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class ResourceTemplateModel implements TemplateDirectiveModel {

//	private DefaultObjectWrapper objectWrapper = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25).build();
	
	private HttpServletRequest request;
	
	private final static String LINK = "<link type=\"text/css\" rel=\"stylesheet\" href=\"%s\" %s />\r\n";
	private final static String SCRIPT = "<script type=\"text/javascript\" src=\"%s\" %s></script>\r\n";
	private final static String IMG = "";

	private ResourceUrlProvider resourceUrlProvider;
	
	public ResourceTemplateModel(HttpServletRequest request, ResourceUrlProvider resourceUrlProvider){
		this.request = request;
		this.resourceUrlProvider = resourceUrlProvider;
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		Object tag = params.get("tag");
		Object src = params.get("src");
		Object attr = params.get("attr");
		Assert.notNull(tag,"@Resource tag属性不能为空");
		Assert.notNull(src,"@Resource src属性不能为空");
		
		String srcStr = String.valueOf(src);
		srcStr = srcStr.startsWith("/") ? srcStr : "/"+srcStr;
		String contextPath = request.getContextPath();
		if(!"".equals(contextPath)){
			srcStr = contextPath + srcStr;
		}
		
		String tagName = String.valueOf(tag).toUpperCase();
		String resourceLocation = getResourceLocation(tagName);
		if(resourceLocation != null){
			srcStr = resourceLocation + srcStr;
		}
		
		if(resourceUrlProvider != null){
			srcStr = resourceUrlProvider.getForLookupPath(srcStr);
		}
		
		if(srcStr == null){
			throw new RuntimeException("页面引入的资源不存在：" + srcStr);
		}
		
		Writer out = env.getOut();
		
		String otherAttr = attr == null ? "" : String.valueOf(attr);
		if(tagName.equals("LINK")){
			out.write(String.format(LINK,srcStr,otherAttr));
		}else if(tagName.equals("SCRIPT")){
			out.write(String.format(SCRIPT,srcStr,otherAttr));
		}else if(tagName.equals("IMG")){
			out.write(String.format(IMG,srcStr,otherAttr));
		}else{
			throw new RuntimeException("不支持的tag名：" + tag+"，目前支持LINK、SCRIPT、IMG。（忽略大小写）");
		}
	}
	
	protected String getResourceLocation(String tagName){
		return null;
	}



}
