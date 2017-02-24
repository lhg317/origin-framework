package com.goldgov.origin.core.web.token;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 提交合法性验证注解，用于注解在方法上，可用于Token的生成和校验。
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
	 * Token处理类型：生成、校验
	 * @author LiuHG
	 * @version 1.0
	 */
	public enum TokenHandleType{
		
		/**
		 * 生成Token令牌，对于RPC请求来说，使用该类型不会有任何效果。因为RPC的Token是通过独立的请求单独获取的，一般使用表单组件{@link com.goldgov.orchid.core.web.client.smartgwt.widgets.form.fields.OWebTokenItem OWebTokenItem}来自动获取Token
		 */
		GENERATE,
		/**
		 * Token令牌的校验，被注解的方法首先检测Token的合法性，通过后才会执行后面的代码逻辑。
		 */
		VERIFY;
	}
}
