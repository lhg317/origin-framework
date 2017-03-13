package com.goldgov.origin.core.web.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goldgov.origin.core.web.annotation.OperateType;

public interface ConstraintValidator<A extends Annotation, T> {
	
	public void initialize(A constraintAnnotation);

	public boolean isValid(String name,T value, Field field,OperateType type,HttpServletRequest request,HttpServletResponse response);
	
	
	public class Utils{
		public static boolean operatingValidate(OperateType type,OperateType[] types){
			for (OperateType type_ : types) {
				if(type == OperateType.NONE || type_ == OperateType.NONE || type_ == type ){
					return true;
				}
			}
			return false;
		}
	}
}