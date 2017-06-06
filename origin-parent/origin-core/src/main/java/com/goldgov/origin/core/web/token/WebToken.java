package com.goldgov.origin.core.web.token;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 提交合法性验证注解，用于注解在方法上，可用于Token的生成和校验。
 * 注意：如果同一用户同时打开两个含有令牌的表单页面，会导致前一个表单页的令牌失效。
 * 默认Token名称为：WEB_TOKEN
 * @author LiuHG
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebToken {

	public final static String DEFAULT_TOKEN_NAME = "WEB_TOKEN";
	
	public TokenHandleType handle();
	
	/**
	 * 当验证失败后跳转到的地址，默认不进行跳转，而是抛出令牌验证失败的异常{@link TokenValidException}。
	 * 也就是在handle为VERIFY时才可能会使用到此值。
	 * @return
	 */
	public String forward() default "";
	
	/**
	 * Token处理类型：生成、校验
	 * @author LiuHG
	 * @version 1.0
	 */
	public enum TokenHandleType{
		
		/**
		 * 生成Token令牌
		 */
		GENERATE,
		/**
		 * Token令牌的校验，被注解的方法首先检测Token的合法性，通过后才会执行后面的代码逻辑。
		 */
		VERIFY;
	}
}
