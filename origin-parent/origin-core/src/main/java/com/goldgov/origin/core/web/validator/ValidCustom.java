package com.goldgov.origin.core.web.validator;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Target({ METHOD, FIELD, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface ValidCustom {

	/**
	 * 判断是否在检测到验证失败的字段时直接由框架以异常形式抛出，此种情况是不能在代码中进行捕获并处理的。
	 * 如果需要程序进行捕获，需设置该参数为false，然后在代码中通过{@link com.goldgov.puzzle.core.web.ValidResultHolder#getErrorFields ValidResultHolder.getErrorFields()}来获取到验证失败的字段信息。
	 * @return true 遇到检测失败的字段由框架直接抛出异常，false 遇到检测失败的字段不进行处理，需要程序进行捕获并处理。
	 */
	public boolean throwException() default true;
	
	public Class<? extends CustomConstraintValidator> constraintValidator();
}
