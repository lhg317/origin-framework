package com.goldgov.origin.core.web.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

public class DateLongEditor extends PropertyEditorSupport{

	private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-M-d H:m:s");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long dateLong = 0;
		
		if(text != null || StringUtils.hasText(text)){
			if(text.indexOf("-") > 0){
				Date date = null;
				try {
					date = dateTimeFormat.parse(text);
				} catch (ParseException e) {
					try {
						date = dateFormat.parse(text);
					} catch (ParseException e1) {
						throw new IllegalArgumentException(e);
					}
				}
				dateLong = date.getTime();
			}else{
				dateLong = NumberUtils.parseNumber(text, Long.class);
			}
		}
		
		super.setValue(dateLong);
	}

}
