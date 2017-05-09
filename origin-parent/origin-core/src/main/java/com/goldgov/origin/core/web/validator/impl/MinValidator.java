package com.goldgov.origin.core.web.validator.impl;

import java.lang.reflect.Field;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goldgov.origin.core.web.annotation.OperateType;
import com.goldgov.origin.core.web.validator.ConstraintValidator;
import com.goldgov.origin.core.web.validator.annotation.Min;

public class MinValidator implements ConstraintValidator<Min,String>{
	
	private long min;
	private OperateType[] types;
	
	@Override
	public void initialize(Min constraintAnnotation) {
		min = constraintAnnotation.min();
		types = constraintAnnotation.type();
	}

	@Override
	public boolean isValid(String name, String value, Field field, OperateType type, HttpServletRequest request,
			HttpServletResponse response) {
		if(Utils.operatingValidate(type, types)){
			if(value == null){
				return true;
			}
			
			if(field.getType() == String.class){
				if(value.length() >= min){
					return true;
				}
			}else if(field.getType() == Integer.class){
				Integer intValue = Integer.valueOf(value);
				if(intValue >= min){
					return true;
				}
			}else if(field.getType() == Double.class){
				Double doubleValue = Double.valueOf(value);
				if(doubleValue >= min){
					return true;
				}
			}else if(field.getType() == Long.class){
				Long longValue = Long.valueOf(value);
				if(longValue >= min){
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
