package com.goldgov.origin.core.web.freemarker.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.goldgov.origin.core.web.token.WebToken;

import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

@SuppressWarnings("rawtypes")
public class WebTokenTemplateModel implements TemplateMethodModelEx {

	private final Log logger = LogFactory.getLog(getClass());
	
	private HttpServletRequest request;
	
	private String tokenName = WebToken.DEFAULT_TOKEN_NAME;
	
	private boolean generateHtml = false;

	private String hiddenTemplate = "<input type=\"hidden\" name=\"%s\" value=\"%s\">";
	
	public WebTokenTemplateModel(HttpServletRequest request){
		this.request = request;
		
	}
	
	@Override
	public Object exec(List paramList) throws TemplateModelException {
		processParams(paramList);
		
		HttpSession session = request.getSession();
		Object tokenValue = session.getAttribute(tokenName);
		tokenValue = tokenValue == null? "":tokenValue;
		String returnStr = tokenValue.toString();
		
		if(logger.isWarnEnabled() && "".equals(returnStr)){
			logger.warn("tokenValue值为空，请确定在controller对应的方法添加了注解@WebToken(handle=TokenHandleType.GENERATE)");
		}
		
		if(generateHtml){
			returnStr = String.format(hiddenTemplate, tokenName,tokenValue);
		}
		return returnStr;
	}

	
	private void processParams(List paramList) throws TemplateModelException {
		if(paramList.size() == 1){
			Object param = paramList.get(0);
			if(param != null){
				if(param instanceof TemplateBooleanModel){
					generateHtml = ((TemplateBooleanModel)param).getAsBoolean();
				}else{
					tokenName = String.valueOf(param);
				}
			}
		}else if(paramList.size() == 2){
			Object tokenName_ = paramList.get(0);
			if(tokenName_ != null){
				tokenName = String.valueOf(tokenName_);
			}
			
			Object generateHtml_ = paramList.get(1);
			if(generateHtml_ != null){
				generateHtml = ((TemplateBooleanModel)generateHtml_).getAsBoolean();
			}
		}
		
	}

}
