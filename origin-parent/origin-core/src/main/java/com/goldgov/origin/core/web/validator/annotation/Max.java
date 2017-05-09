package com.goldgov.origin.core.web.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.goldgov.origin.core.web.annotation.OperateType;
import com.goldgov.origin.core.web.validator.Constraint;
import com.goldgov.origin.core.web.validator.impl.MaxValidator;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = MaxValidator.class)
public @interface Max {

	public long max();
	
	public OperateType[] type() default OperateType.NONE;
	
	public String message() default "{fieldDesc}最大值不能超过{max}";
	
	public String fieldName() default "";
	public String fieldDesc() default "";
}
