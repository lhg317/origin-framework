package com.goldgov.origin.core.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MyBatis的持久接口标记注解，被标记该注解的接口会被识别为MyBatis的数据访问对象。
 * @author LiuHG
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mapper {

	public String value() default "";
}
