package com.goldgov.origin.core.web.resolver;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.thrift.TBase;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.ModelFactory;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 用于RPC属性绑定
 * @author LiuHG
 * @version 1.0
 */
public class RpcBeanResolver implements HandlerMethodArgumentResolver{//extends ServletModelAttributeMethodProcessor

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Class<?> parameterType = parameter.getParameterType();
//		String name = parameterType.getSimpleName();
//		if (name.startsWith("Rpc")) {
		if (parameterType.isAssignableFrom(TBase.class)) {
			return true;
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String attributeName = ModelFactory.getNameForParameter(parameter);
		
		Object target = mavContainer.containsAttribute(attributeName) ?
				mavContainer.getModel().get(attributeName) :BeanUtils.instantiate(parameter.getParameterType());
		
		WebDataBinder binder = binderFactory.createBinder(webRequest, target, attributeName);
//		ServletRequestDataBinder servletBinder = (ServletRequestDataBinder) binder;
//        servletBinder.bind((ServletRequest) webRequest.getNativeRequest());
//        servletBinder.setIgnoreInvalidFields(true);
		
        MutablePropertyValues mpvs = new MutablePropertyValues(((ServletRequest) webRequest.getNativeRequest()).getParameterMap());
        PropertyValue[] propertyValues = mpvs.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues) {
        	Object object = propertyValue.getValue();
        	if(object != null){
        		String[] paramValue = (String[])object;
        		if(paramValue.length == 1 && !StringUtils.hasText(paramValue[0])){
        			 mpvs.removePropertyValue(propertyValue.getName());
        		}
        	}
		}
        binder.bind(mpvs);
        
        if (binder.getBindingResult().hasErrors() && isBindExceptionRequired(binder, parameter)) {
			throw new BindException(binder.getBindingResult());
		}
        
//        Add resolved attribute and BindingResult at the end of the model
        Map<String, Object> bindingResultModel = binder.getBindingResult().getModel();
		mavContainer.removeAttributes(bindingResultModel);
		mavContainer.addAllAttributes(bindingResultModel);
		return target;
	}
	
	protected boolean isBindExceptionRequired(WebDataBinder binder, MethodParameter methodParam) {
		int i = methodParam.getParameterIndex();
		Class<?>[] paramTypes = methodParam.getMethod().getParameterTypes();
		boolean hasBindingResult = (paramTypes.length > (i + 1) && Errors.class.isAssignableFrom(paramTypes[i + 1]));
		return !hasBindingResult;
	}

}
