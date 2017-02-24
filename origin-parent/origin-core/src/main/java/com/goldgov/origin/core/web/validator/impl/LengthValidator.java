package com.goldgov.origin.core.web.validator.impl;

import java.lang.reflect.Field;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goldgov.origin.core.web.annotation.OperatingType;
import com.goldgov.origin.core.web.validator.ConstraintValidator;
import com.goldgov.origin.core.web.validator.annotation.Length;

public class LengthValidator implements ConstraintValidator<Length,String>{

	private int min;
	private int max;

	private OperatingType[] types;
	
	@Override
	public void initialize(Length constraintAnnotation) {
		types = constraintAnnotation.type();
		
		min = constraintAnnotation.min();
		max = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(String name,String value, Field field, OperatingType type,HttpServletRequest request,HttpServletResponse response) {
		if(Utils.operatingValidate(type, types)){
			if(value == null){
				return true;
			}
			
			if ( max < min ) {
				throw new IllegalArgumentException( "The length cannot be negative." );
			}
			
			if(field.getType() == String.class){
				if(value.length() >= min && value.length() <= max){
					return true;
				}
			}else if(field.getType() == Integer.class){
				Integer intValue = Integer.valueOf(value);
				if(intValue >= min && intValue <= max){
					return true;
				}
			}else if(field.getType() == Double.class){
				Double doubleValue = Double.valueOf(value);
				if(doubleValue >= min && doubleValue <= max){
					return true;
				}
			}else if(field.getType() == Long.class){
				Long longValue = Long.valueOf(value);
				if(longValue >= min && longValue <= max){
					return true;
				}
			}else if(field.getType() == Date.class){
				throw new RuntimeException("暂不支持Date类型的转换判断，请参考@Future和@Past注解的使用");
			}else{
				throw new RuntimeException("不支持类型的转换判断：" + field.getType());
			}
			
			return false;
		}
		return true;
	}
	

}
