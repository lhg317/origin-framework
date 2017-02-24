package com.goldgov.origin.core.web.validator.impl;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goldgov.origin.core.web.annotation.OperatingType;
import com.goldgov.origin.core.web.validator.ConstraintValidator;
import com.goldgov.origin.core.web.validator.annotation.Past;

public class PastValidator implements ConstraintValidator<Past,String>{

	private SimpleDateFormat dateFormat = new SimpleDateFormat();
	private Date targetDate;

	private OperatingType[] types;
	
	@Override
	public void initialize(Past constraintAnnotation) {
		types = constraintAnnotation.type();
		
		String pattern = constraintAnnotation.pattern();
		dateFormat.applyPattern(pattern);
		
		String dateTime = constraintAnnotation.dateTime();
		if("".equals(dateTime)){
			targetDate = new Date();
		}else{
			try {
				targetDate = dateFormat.parse(dateTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public boolean isValid(String name,String value, Field field,OperatingType type,HttpServletRequest request,HttpServletResponse response) {
		if(Utils.operatingValidate(type, types)){
			try {
				Date date = dateFormat.parse(value);
				date.before(targetDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		return true;
	}

}
