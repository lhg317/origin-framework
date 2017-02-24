package com.goldgov.origin.core.web.validator.annotation;


public @interface FieldDescription {

	public String fieldName() default "";
	
	public String fieldDesc() default "";
	
//	public OperatingType[] type() default OperatingType.None;
	
}
