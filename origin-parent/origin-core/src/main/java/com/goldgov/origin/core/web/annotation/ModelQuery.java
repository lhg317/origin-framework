package com.goldgov.origin.core.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModelQuery {

	public String value() default "";
	
	/**
	 * 排序字段参数名
	 * @return
	 */
	public String fieldName() default "sort.name";
	
	/**
	 * 排序方式（正序、倒序）字段参数名
	 * @return
	 */
	public String directionName() default "sort.direction";
	
	/**
	 * 最大显示的最大记录数，用来控制通过传参<code>pageSize</code>来修改页面的显示数量
	 * @return 最大记录数，默认不控制显示数量
	 */
	public int pageLimitSize() default -1;
}
