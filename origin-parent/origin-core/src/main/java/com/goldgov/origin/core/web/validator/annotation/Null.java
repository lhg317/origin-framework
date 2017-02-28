package com.goldgov.origin.core.web.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.goldgov.origin.core.web.annotation.OperateType;
import com.goldgov.origin.core.web.validator.Constraint;
import com.goldgov.origin.core.web.validator.impl.NullValidator;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = NullValidator.class)
public @interface Null {

	public String fieldName() default "";
	
	public String fieldDesc() default "";
	
	public OperateType[] type() default OperateType.None;
	
	public String message() default "{fieldDesc}必须为空";
	
	
}
