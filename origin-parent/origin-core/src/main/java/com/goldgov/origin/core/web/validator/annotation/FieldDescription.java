package com.goldgov.origin.core.web.validator.annotation;

/**
 * 
 * @author LiuHG
 * @version 1.0
 */
public @interface FieldDescription {

	public String fieldName() default "";
	
	public String fieldDesc() default "";
	
	
}
