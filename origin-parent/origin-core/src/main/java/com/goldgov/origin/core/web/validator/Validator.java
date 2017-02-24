package com.goldgov.origin.core.web.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goldgov.origin.core.web.annotation.OperatingType;

/**
 * 自定义字段验证器
 * @author LiuHG
 * @version 1.0
 */
public interface Validator {

	/**
	 * 判断给定值是否合法
	 * @param value 字段值
	 * @param type 当前的操作类型
	 * @param types 验证器限定的操作类型（注解中指定的操作类型）
	 * @param request 
	 * @param response
	 * @return
	 */
	boolean isValid(String name,String value, OperatingType type,
			OperatingType[] types, HttpServletRequest request, HttpServletResponse response);
}
