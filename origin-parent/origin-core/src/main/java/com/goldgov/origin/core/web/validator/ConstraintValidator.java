package com.goldgov.origin.core.web.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goldgov.origin.core.web.annotation.OperatingType;

public interface ConstraintValidator<A extends Annotation, T> {
	
	public void initialize(A constraintAnnotation);

	public boolean isValid(String name,T value, Field field,OperatingType type,HttpServletRequest request,HttpServletResponse response);
	
	
	public class Utils{
		public static boolean operatingValidate(OperatingType type,OperatingType[] types){
			for (OperatingType type_ : types) {
				if(type == OperatingType.None || type_ == OperatingType.None || type_ == type ){
					return true;
				}
			}
			return false;
		}
	}
}