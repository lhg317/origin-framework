package com.goldgov.origin.core.web.validator.impl;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goldgov.origin.core.web.annotation.OperateType;
import com.goldgov.origin.core.web.validator.ConstraintValidator;
import com.goldgov.origin.core.web.validator.annotation.NotNull;

public class NotNullValidator implements ConstraintValidator<NotNull,String>{

	private OperateType[] types;

	@Override
	public void initialize(NotNull constraintAnnotation) {
		types = constraintAnnotation.type();
	}

	@Override
	public boolean isValid(String name,String value, Field field,OperateType type,HttpServletRequest request,HttpServletResponse response) {
		if(Utils.operatingValidate(type, types)){
			return value != null && !"".equals(value);
		}
		return true;
	}

}
