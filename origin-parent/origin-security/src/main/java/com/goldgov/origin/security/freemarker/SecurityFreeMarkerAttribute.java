package com.goldgov.origin.security.freemarker;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.goldgov.origin.core.web.freemarker.FreeMarkerAttribute;
import com.goldgov.origin.security.freemarker.model.AuthorizeTemplateModel;
import com.goldgov.origin.security.freemarker.model.CsrfTokenTemplateModel;

@Component
public class SecurityFreeMarkerAttribute implements FreeMarkerAttribute {

	@Override
	public void attributesMap(Map<String, Object> attributes) {
		attributes.put("csrfToken", new CsrfTokenTemplateModel());
		attributes.put("authorize", new AuthorizeTemplateModel());
		
	}

}
