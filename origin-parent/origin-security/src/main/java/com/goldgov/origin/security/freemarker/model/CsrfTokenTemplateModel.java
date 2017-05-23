package com.goldgov.origin.security.freemarker.model;

import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;

import com.goldgov.origin.core.web.interceptor.handler.impl.RequestHolderHandler.RequestHolder;

import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

@SuppressWarnings("rawtypes")
public class CsrfTokenTemplateModel implements TemplateMethodModelEx {

	private boolean generateHtml = false;

	private String hiddenTemplate = "<input type=\"hidden\" name=\"%s\" value=\"%s\">";
	
	@Override
	public Object exec(List paramList) throws TemplateModelException {
		processParams(paramList);
		
		CsrfToken csrfToken = (CsrfToken)RequestHolder.getRequest().getAttribute("_csrf");
		if(csrfToken == null){
			return "";
		}
		
		String returnStr;
		if(generateHtml){
			returnStr = String.format(hiddenTemplate, csrfToken.getParameterName(),csrfToken.getToken());
		}else{
			returnStr = csrfToken.getToken();
		}
		return returnStr;
	}

	
	private void processParams(List paramList) throws TemplateModelException {
		if(paramList.size() == 1){
			Object param = paramList.get(0);
			if(param != null){
				if(param instanceof TemplateBooleanModel){
					generateHtml = ((TemplateBooleanModel)param).getAsBoolean();
				}
			}
		}
	}

}
