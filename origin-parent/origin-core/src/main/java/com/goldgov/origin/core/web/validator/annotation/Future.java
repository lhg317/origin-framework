package com.goldgov.origin.core.web.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.goldgov.origin.core.web.annotation.OperateType;
import com.goldgov.origin.core.web.validator.Constraint;
import com.goldgov.origin.core.web.validator.impl.FutureValidator;

/**
 * 限制必须是一个将来的日期
 * @author LiuHG
 * @version 1.0
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = FutureValidator.class)
public @interface Future {

	public String fieldName() default "";
	
	public String fieldDesc() default "";
	
	public OperateType[] type() default OperateType.None;
	
	public String message() default "{fieldDesc}日期必须晚于{dateTime}";
	
	public String pattern() default "yyyy-MM-dd HH:mm:ss";
	
	public String dateTime() default "";
}
