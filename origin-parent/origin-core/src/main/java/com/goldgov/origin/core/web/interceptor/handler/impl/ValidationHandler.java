package com.goldgov.origin.core.web.interceptor.handler.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.goldgov.origin.core.Keys;
import com.goldgov.origin.core.web.annotation.ModuleOperating;
import com.goldgov.origin.core.web.annotation.OperateType;
import com.goldgov.origin.core.web.interceptor.handler.IRequestHandler;
import com.goldgov.origin.core.web.interceptor.handler.impl.LocaleChangeHandler.MessagesHolder;
import com.goldgov.origin.core.web.validator.Constraint;
import com.goldgov.origin.core.web.validator.ConstraintValidator;
import com.goldgov.origin.core.web.validator.CustomConstraintValidator;
import com.goldgov.origin.core.web.validator.Valid;
import com.goldgov.origin.core.web.validator.ValidCustom;
import com.goldgov.origin.core.web.validator.ValidationError;
import com.goldgov.origin.core.web.validator.ValidationException;
import com.goldgov.origin.core.web.validator.annotation.Custom;
import com.goldgov.origin.core.web.validator.annotation.Email;
import com.goldgov.origin.core.web.validator.annotation.FieldDescription;
import com.goldgov.origin.core.web.validator.annotation.Future;
import com.goldgov.origin.core.web.validator.annotation.Length;
import com.goldgov.origin.core.web.validator.annotation.Max;
import com.goldgov.origin.core.web.validator.annotation.Min;
import com.goldgov.origin.core.web.validator.annotation.NotContain;
import com.goldgov.origin.core.web.validator.annotation.NotNull;
import com.goldgov.origin.core.web.validator.annotation.Null;
import com.goldgov.origin.core.web.validator.annotation.Past;
import com.goldgov.origin.core.web.validator.annotation.Pattern;

/**
 * MVC拦截器处理器，负责字段服务端校验
 * @author LiuHG
 * @version 1.0
 */
public class ValidationHandler implements IRequestHandler{

	private static List<Class<? extends Annotation>> validatorList = new ArrayList<Class<? extends Annotation>>();
	
	private Map<Class<? extends ConstraintValidator<?, ?>>,ConstraintValidator<?,?>> constraintValidatorMap = new HashMap<Class<? extends ConstraintValidator<?, ?>>,ConstraintValidator<?,?>>();
	
	private PropertyPlaceholderHelper propertyPlaceholderHelper = new PropertyPlaceholderHelper("{","}");
	
	static{
		validatorList.add(NotNull.class);
		validatorList.add(Length.class);
		validatorList.add(Pattern.class);
		validatorList.add(Email.class);
		validatorList.add(Future.class);
		validatorList.add(Past.class);
		validatorList.add(Null.class);
		validatorList.add(NotContain.class);
		validatorList.add(Max.class);
		validatorList.add(Min.class);
		validatorList.add(Custom.class);
	}
	
//	private ThreadLocal<List<ObjectError>> errorThreadLocal = new ThreadLocal<List<ObjectError>>();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = ((HandlerMethod)handler);
			//判断被请求的方法参数是否有被标注@Valid注解的（被标注@Valid的说明需要进行字段校验）
			MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
			if(methodParameters != null && methodParameters.length > 0){
				for (int i = 0; i < methodParameters.length; i++) {
					
					Valid valid = methodParameters[i].getParameterAnnotation(Valid.class);
					if(valid != null){
						List<ValidationError> errorList = new ArrayList<ValidationError>();
						
						//判断需要被验证参数的方法上是否标注了@ModuleOperating注解，用于得到方法操作类型，否则方法操作类型被置为None
						Method method = methodParameters[i].getMethod();
						ModuleOperating operatingAnno = method.getAnnotation(ModuleOperating.class);
						OperateType optType = null;
						if(operatingAnno == null){
							optType = OperateType.NONE;
						}else{
							optType = operatingAnno.type();
						}
						
						//得到需要验证的方法参数对象，获取被标注验证注解的字段
						Class<?> parameterType = methodParameters[i].getParameterType();
						Field[] declaredFields = parameterType.getDeclaredFields();
						for (Field field : declaredFields) {
							FieldDescription fieldDesc = field.getAnnotation(FieldDescription.class);
							for (Class<? extends Annotation> clazz : validatorList) {
								Annotation annotation = field.getAnnotation(clazz);
								if(annotation != null){
									Constraint constraint = annotation.annotationType().getAnnotation(Constraint.class);
									
									Assert.notNull(constraint, "验证注解"+annotation+"缺少@Constraint注解");
									
									//判断需要验证的属性字段名称
									Map<String, Object> annoAtt = AnnotationUtils.getAnnotationAttributes(annotation);
									Object fieldName = annoAtt.get("fieldName");
									String paramName = null;
									Object paramValue = null;
									
									if(fieldDesc != null && !"".equals(fieldDesc.fieldName())){
										paramName = fieldDesc.fieldName();
									}else{
										if("".equals(fieldName)){
											paramName = field.getName();
										}else{
											paramName = fieldName.toString();
										}
									}

									//从request中得到参数值，用于验证用。
									if(field.getType().isArray()){
										paramValue = request.getParameterValues(paramName);
									}else{
										paramValue = request.getParameter(paramName);
									}
									
									//得到验证器的处理类，先从缓存中获取，如果不存在则实例化。
									Class<? extends ConstraintValidator<?, ?>> validatedBy = constraint.validatedBy();
									ConstraintValidator validator = constraintValidatorMap.get(validatedBy);
									if(validator == null){
										try {
											validator = validatedBy.newInstance();
											constraintValidatorMap.put(validatedBy, validator);
										} catch (InstantiationException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (IllegalAccessException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									//验证字段的正确性，如果未验证通过，则抛出异常。
									validator.initialize(annotation);
									boolean pass = validator.isValid(paramName,paramValue,field, optType,request,response);
									if(!pass){
										//Properties不能够获取非String类型的值，因此不能直接用putAll
										Properties properties = new Properties();
										for (Entry<String, Object> entry : annoAtt.entrySet()) {
											String name = entry.getKey();
											String value = String.valueOf(entry.getValue());
											if("fieldName".equals(name) || "fieldDesc".equals(name)){
												properties.setProperty(name,MessagesHolder.getMessageByPrefix(value));
											}else{
												properties.setProperty(name,value);
											}
										}
										
										if(paramValue != null){
											properties.put("value", paramValue);
										}
										properties.remove("message");
										
										if(fieldDesc != null && !"".equals(fieldDesc.fieldDesc())){
											properties.put("fieldDesc", fieldDesc.fieldDesc());
										}else if("".equals(annoAtt.get("fieldDesc"))){
											properties.put("fieldDesc",paramName);
										}
										
										String message = propertyPlaceholderHelper.replacePlaceholders(MessagesHolder.getMessageByPrefix(annoAtt.get("message").toString()), properties);
										errorList.add(new ValidationError(MessagesHolder.getMessageByPrefix(properties.getProperty("fieldDesc")),message));
										
//										throw new ValidationException(message);
//										return false;
									}
								}
							}
						}
						if(errorList.size() > 0){
							if(valid.throwException()){
								throw new ValidationException(errorList,errorMessage(errorList));
							}else{
								request.setAttribute(Keys.VALIDATION_ERROR, errorList.toArray(new ValidationError[0]));
							}
						}
					}else{
						ValidCustom validCustom = methodParameters[i].getParameterAnnotation(ValidCustom.class);
						if(validCustom != null){
							Class<? extends CustomConstraintValidator> constraintValidator = validCustom.constraintValidator();
							CustomConstraintValidator validator = BeanUtils.instantiateClass(constraintValidator);
							
							//判断需要被验证参数的方法上是否标注了@ModuleOperating注解，用于得到方法操作类型，否则方法操作类型被置为None
							Method method = methodParameters[i].getMethod();
							ModuleOperating operatingAnno = method.getAnnotation(ModuleOperating.class);
							OperateType optType = null;
							if(operatingAnno == null){
								optType = OperateType.NONE;
							}else{
								optType = operatingAnno.type();
							}
							boolean validResult = false;
							try {
								validResult = validator.isValid(optType,request,response);
								if(!validResult){
									ValidationException validationException = new ValidationException(new ArrayList<ValidationError>(),"验证失败");
									if(validCustom.throwException()){
										throw validationException;
									}else{
										request.setAttribute(Keys.VALIDATION_ERROR, validationException.getErrors().toArray(new ValidationError[0]));
									}
								}
							} catch (ValidationException e) {
								if(validCustom.throwException()){
									throw e;
								}else{
									request.setAttribute(Keys.VALIDATION_ERROR, e.getErrors().toArray(new ValidationError[0]));
								}
							}
						}
					}
					
				}
			}
		}
		return true;
	}
	
	protected String errorMessage(List<ValidationError> errorList){
		if(errorList == null){
			return "";
		}
		
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < errorList.size(); i++) {
			strBuilder.append(errorList.get(i).getMessage() + ";");
		}
		return strBuilder.toString();
	}

	@Override
	public boolean postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
//			List<ObjectError> errorList = errorThreadLocal.get();
//			if(errorList != null && errorList.size() > 0){
//				Collection<Object> values = modelAndView.getModel().values();
//				for(Object obj : values){
//					if(obj instanceof BindingResult){
//						BindingResult bindingResult = ((BindingResult)obj);
//						for (ObjectError objectError : errorList) {
//							bindingResult.addError(objectError);
//						}
//					}
//				}
//				return false;
//			}
		return true;
	}

	@Override
	public void completion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
