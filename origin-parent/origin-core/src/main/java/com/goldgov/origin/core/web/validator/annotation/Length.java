package com.goldgov.origin.core.web.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.goldgov.origin.core.web.annotation.OperateType;
import com.goldgov.origin.core.web.validator.Constraint;
import com.goldgov.origin.core.web.validator.impl.LengthValidator;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = LengthValidator.class)
public @interface Length {
	public int min();
	public int max();
	public OperateType[] type() default OperateType.NONE;
	public String message() default "{fieldDesc}长度最小{min}最大{max}";
}
