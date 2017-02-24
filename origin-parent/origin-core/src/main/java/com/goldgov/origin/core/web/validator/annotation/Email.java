package com.goldgov.origin.core.web.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.goldgov.origin.core.web.annotation.OperatingType;
import com.goldgov.origin.core.web.validator.Constraint;
import com.goldgov.origin.core.web.validator.impl.EmailValidator;


@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {

	public String fieldName() default "";
	
	public String fieldDesc() default "";
	
	public OperatingType[] type() default OperatingType.None;
	
	public String message() default "{fieldDesc}邮件格式错误";
}
