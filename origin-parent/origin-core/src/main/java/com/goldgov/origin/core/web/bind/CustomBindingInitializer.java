package com.goldgov.origin.core.web.bind;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.goldgov.origin.core.web.propertyeditor.DateLongEditor;

public class CustomBindingInitializer extends ConfigurableWebBindingInitializer{

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		super.initBinder(binder, request);
		DateLongEditor longDateEditor = new DateLongEditor();
		binder.registerCustomEditor(Long.TYPE, longDateEditor);
		binder.registerCustomEditor(Long.class, longDateEditor);
//		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	
}
