package com.goldgov.origin.core.web.validator.impl;

import java.lang.reflect.Field;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goldgov.origin.core.web.annotation.OperatingType;
import com.goldgov.origin.core.web.validator.ConstraintValidator;
import com.goldgov.origin.core.web.validator.annotation.Email;

public class EmailValidator implements ConstraintValidator<Email, String> {
	
	private OperatingType[] types;
	
	private static String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
	private static String DOMAIN = "(" + ATOM + "+(\\." + ATOM + "+)*";
	private static String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";

	private java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
			"^" + ATOM + "+(\\." + ATOM + "+)*@"
					+ DOMAIN
					+ "|"
					+ IP_DOMAIN
					+ ")$",
			java.util.regex.Pattern.CASE_INSENSITIVE
	);

	public void initialize(Email constraintAnnotation) {
		types = constraintAnnotation.type();
	}

	public boolean isValid(String name,String value, Field field,OperatingType type,HttpServletRequest request,HttpServletResponse response) {
		if(Utils.operatingValidate(type, types)){
			if ( value == null || value.length() == 0 ) {
				return true;
			}
			Matcher m = pattern.matcher( value );
			return m.matches();
		}
		return true;
		
	}
}

