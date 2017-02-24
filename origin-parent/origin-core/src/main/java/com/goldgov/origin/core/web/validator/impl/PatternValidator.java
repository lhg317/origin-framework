package com.goldgov.origin.core.web.validator.impl;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goldgov.origin.core.web.annotation.OperatingType;
import com.goldgov.origin.core.web.validator.ConstraintValidator;
import com.goldgov.origin.core.web.validator.annotation.Pattern;

public class PatternValidator implements ConstraintValidator<Pattern,String>{

	private java.util.regex.Pattern pattern;
	
	private OperatingType[] types;
	
	@Override
	public void initialize(Pattern constraintAnnotation) {
		types = constraintAnnotation.type();
		
		Pattern.Flag flags[] = constraintAnnotation.flags();
		int intFlag = 0;
		for ( Pattern.Flag flag : flags ) {
			intFlag = intFlag | flag.getValue();
		}

		try {
			pattern = java.util.regex.Pattern.compile( constraintAnnotation.regexp(), intFlag );
		}
		catch ( PatternSyntaxException e ) {
			throw new IllegalArgumentException( "Invalid regular expression.", e );
		}
	}

	@Override
	public boolean isValid(String name,String value, Field field,OperatingType type,HttpServletRequest request,HttpServletResponse response) {
		if(Utils.operatingValidate(type, types)){
			if ( value == null ) {
				return true;
			}
			Matcher m = pattern.matcher( value );
			return m.matches();
		}
		return true;
		
	}

}
