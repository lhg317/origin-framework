package com.goldgov.origin.core.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模块操作注解
 * @author LiuHG
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleOperating {

	/**
	 * 模块操作名，用于角色资源选择时显示，如果名称以“i18n:”开头，则表示需要从资源文件中获取真正的显示名称，“i18n:”后面的部分为资源key
	 * @return 操作名称
	 */
	public String name() default "";
	
	/**
	 * 资源编码，默认赋值为type().toString()
	 * @return 资源编码
	 */
	public String code() default "";
	/**
	 * 模块操作类型，用于对需要限制到按钮及权限时使用
	 * @return 该操作所属类型
	 */
	public OperatingType type() default OperatingType.None;
	
	/**
	 * 是否需要认证后才可以访问
	 * @return true为需要认证，false为不需要认证。默认为true
	 */
	public boolean needAuth() default true;
}
