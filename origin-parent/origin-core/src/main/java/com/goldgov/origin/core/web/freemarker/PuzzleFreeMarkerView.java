package com.goldgov.origin.core.web.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.goldgov.origin.core.web.freemarker.model.I18nTemplateModel;
import com.goldgov.origin.core.web.freemarker.model.ResourceTemplateModel;
import com.goldgov.origin.core.web.freemarker.model.WebTokenTemplateModel;

import freemarker.template.SimpleHash;

public class PuzzleFreeMarkerView extends FreeMarkerView{

	@Override
	protected SimpleHash buildTemplateModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response) {
		SimpleHash templateModel = super.buildTemplateModel(model, request, response);
		templateModel.put("webToken", new WebTokenTemplateModel(request));
		
//		HttpSession session = request.getSession();
//		Object locale = session.getAttribute(Keys.DEFAULT_LOCALE);
//		if(locale != null){
//			locale = RequestContextUtils.getLocale(request);
//		}
		
		ResourceUrlProvider resourceUrlProvider = BeanFactoryUtils.beanOfTypeIncludingAncestors(getApplicationContext(), ResourceUrlProvider.class, true, false);
		
		templateModel.put("i18n", new I18nTemplateModel());
		templateModel.put("res", new ResourceTemplateModel(request,resourceUrlProvider));
		
//		templateModel.put("localContextPath", request.getContextPath());
		
		return templateModel;
	}
	
}
