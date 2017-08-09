package com.goldgov.origin.modules.basedata.freemarker;

import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.goldgov.origin.core.web.freemarker.FreeMarkerAttribute;
import com.goldgov.origin.modules.basedata.freemarker.model.BaseDataTemplateModel;

@Component
@Scope(scopeName=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseDataFreeMarkerAttribute implements FreeMarkerAttribute {

	
	@Override
	public void attributesMap(Map<String, Object> attributes) {
		attributes.put("baseData", new BaseDataTemplateModel());
	}

}
