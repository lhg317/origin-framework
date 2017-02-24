package com.goldgov.origin.core.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模块资源注解
 * @author LiuHG
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleResource {

	/**
	 * 设置模块的名称。<br>
	 * 如果名称以“i18n:”开头，则表示需要从资源文件中获取真正的显示名称，“i18n:”后面的部分为资源code。<br>
	 * 如果名称中包含“:”，则会议第一个“:”之前的部分作为国际化语言标记去资源文件获取对应语言的文字。<br>
	 * 示例：<br>
	 * <pre>
	 * 	i18n:title	->	根据语言环境获取对应的文字：“标题”或“Title”等
	 * 	zh_CN:title	->	标题
	 * 	en_US:title	->	Title
	 * 	标题		->	标题
	 * </pre>
	 * @return 模块的名称
	 */
	public String name() default "";
	
	/**
	 * 模块资源编码，所有资源必须保证唯一。
	 * @return 模块资源编码
	 */
	public String code() default "";
	
	/**
	 * 设置模块路径，另外的用途就是对模块进行分类。
	 * @return 模块所属路径
	 */
	public String[] path() default {};
	
	/**
	 * 模块资源是否可见，只是可显示设置，不会停用资源的使用。
	 * @return 是否可见，默认为true
	 */
	public boolean isVisible() default true;
	
//	/**
//	 * 资源类型
//	 * @return
//	 */
//	public ModuleResourceType[] type();
//	
//	
//	/**
//	 * 资源类型
//	 * @author LiuHG
//	 * @version 1.0
//	 */
//	public enum ModuleResourceType{
//		RPC,URL;
//	}
}
