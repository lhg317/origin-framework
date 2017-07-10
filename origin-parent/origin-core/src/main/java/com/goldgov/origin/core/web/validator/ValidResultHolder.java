package com.goldgov.origin.core.web.validator;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import com.goldgov.origin.core.Keys;
import com.goldgov.origin.core.web.interceptor.handler.impl.LocaleChangeHandler.MessagesHolder;
import com.goldgov.origin.core.web.interceptor.handler.impl.RequestHolderHandler.RequestHolder;
/**
 * 获取验证失败的字段，如果未包含验证失败的字段，则返回<code>new ValidationError[0]</code>，而不是<code>null</code>。
 * 此处的验证特指通过服务端验证注解来实现的字段值的校验，同时需要验证的对象需要标注{@link com.goldgov.origin.core.web.validator.Valid @Valid}注解。<p>
 * 该对象仅能用在通过<code>DispatcherServlet</code>的请求，不能直接应用于*.jsp请求的页面。
 * @author LiuHG
 * @version 1.0
 * @see {@link com.goldgov.origin.core.web.interceptor.handler.impl.ValidationHandler ValidationHandler}
 */
public abstract class ValidResultHolder {

	/**
	 * 获得当前请求验证失败的字段，如果不存在，返回<code>new ValidationError[0]</code>
	 * @return 验证失败的字段
	 */
	public static ValidationError[] getErrorFields(){
		return getErrorFields(null);
	}
	
	/**
	 * 获得当前请求验证失败的字段，如果不存在，返回<code>new ValidationError[0]</code>
	 * @param request 当前请求对象，从指定的request对象中获取失败字段信息。
	 * @return 验证失败的字段
	 */
	public static ValidationError[] getErrorFields(HttpServletRequest request){
		Object errors = null;
		if(request != null){
			errors = request.getAttribute(Keys.VALIDATION_ERROR);
		}else{
			errors = RequestHolder.getRequest().getAttribute(Keys.VALIDATION_ERROR);
		}
		if(errors != null){
			ValidationError[] validationError = (ValidationError[])errors;
			return validationError;
		}else{
			return new ValidationError[0];
		}
	}
	
	/**
	 * 判断当前请求中时候包含服务端验证失败的字段
	 * @return true 包含验证错误的字段，false 不包含验证错误的字段
	 */
	public static boolean hasError(){
		ValidationError[] errorFields = getErrorFields();
		return errorFields.length > 0;
	}
	
	/**
	 * 判断当前请求中时候包含服务端验证失败的字段
	 * @param request 当前请求对象，从指定的request对象中获取失败字段信息。
	 * @return true 包含验证错误的字段，false 不包含验证错误的字段
	 */
	public static boolean hasError(HttpServletRequest request){
		ValidationError[] errorFields = getErrorFields(request);
		return errorFields.length > 0;
	}
	
	public static String getErrorMessage(){
		return getErrorMessage(null);
	}
	
	public static String getErrorMessage(HttpServletRequest request){
		return getErrorMessage(request,null);
	}
	
	/**
	 * 获取错误字段消息
	 * @param request 当前请求对象，从指定的request对象中获取失败字段信息。
	 * @param format {0} 代表字段显示名；{1} 代表验证失败的消息内容；{2} 序号
	 * @return
	 */
	public static String getErrorMessage(HttpServletRequest request,String format){
		ValidationError[] errorFields = getErrorFields(request);
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < errorFields.length; i++) {
			if(format != null){
				stringBuilder.append(MessageFormat.format(format, errorFields[i].getFieldName(),errorFields[i].getMessage(),(i+1)));
			}else{
				stringBuilder.append(errorFields[i] + MessagesHolder.getMessage("semicolon"));
			}
		}
		return stringBuilder.toString();
	}
}
