package com.goldgov.origin.core.web.validator.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goldgov.origin.core.web.annotation.OperateType;
import com.goldgov.origin.core.web.validator.ConstraintValidator;
import com.goldgov.origin.core.web.validator.Validator;
import com.goldgov.origin.core.web.validator.annotation.Custom;

public class CustomValidator implements ConstraintValidator<Custom, String> {

	private static final Map<Class<? extends Validator>,Validator> validatorMap = new HashMap<Class<? extends Validator>,Validator>();
	
	private Class<? extends Validator> validatorClass;

	private OperateType[] types;

	@Override
	public void initialize(Custom constraintAnnotation) {
		validatorClass = constraintAnnotation.validator();
		types = constraintAnnotation.type();
	}

	@Override
	public boolean isValid(String name,String value, Field field,OperateType type,
			HttpServletRequest request, HttpServletResponse response) {
		Validator v = null;
		if(validatorMap.containsKey(validatorClass)){
			v = validatorMap.get(validatorClass);
		}else{
			try {
				v = validatorClass.newInstance();
				validatorMap.put(validatorClass, v);
			} catch (Exception e) {
				throw new RuntimeException("无法根据给定的验证Class类构造实例，请确保类存在且包含一个无参的构造器：" + validatorClass,e);
			}
		}
		return v.isValid(name,value, type, types,request, response);
	}

}
