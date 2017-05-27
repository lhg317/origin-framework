package com.goldgov.origin.core.web.resolver;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.goldgov.origin.core.service.Query;
import com.goldgov.origin.core.web.annotation.ModelQuery;

/**
 * ModelQuery注解解释器。
 * 
 * @author LiuHG
 * @version 1.0
 */
public class ModelQueryResolver implements HandlerMethodArgumentResolver{

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Class<?> parameterType = parameter.getParameterType();
		if (parameter.hasParameterAnnotation(ModelQuery.class)) {
			boolean isQueryObject =  Query.class.isAssignableFrom(parameterType);
			if(isQueryObject){
				return true;
			}else{
				//TODO 警告需要继承Query
			}
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		
		ModelQuery annot = parameter.getParameterAnnotation(ModelQuery.class);
		
		String attributeName = annot.value();
		Object target = mavContainer.containsAttribute(attributeName) ?
				mavContainer.getModel().get(attributeName) :BeanUtils.instantiate(parameter.getParameterType());
				
		if(!(target instanceof Query)){
			throw new RuntimeException("被标注@ModelQuery的属性必须为一个org.gtiles.core.service.Query<User>子类对象");
		}
		
		Query query = ((Query)target);
//		Map<String, Object> parameters = WebUtils.getParametersStartingWith((HttpServletRequest)webRequest.getNativeRequest(), "sort.");
		
//		//处理排序属性
//		String name = webRequest.getParameter(annot.fieldName());
//		String direction = webRequest.getParameter(annot.directionName());
//		
//		if(name != null){
//			direction = direction == null ? SortDirection.ASC.toString() : direction;
//			
////			HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
////			HttpSession session = request.getSession();
////			session.setAttribute(Keys.CURRENT_SORT_FIELD_NAME, name);
////			session.setAttribute(Keys.CURRENT_SORT_DIRECTION_NAME, direction);
//			
//			SortField sortField = new SortField(name,SortDirection.valueOf(direction.toUpperCase()));
////			Method sortMethod = parameter.getParameterType().getMethod("setSortFields", SortField[].class);
////			ReflectionUtils.invokeMethod(sortMethod, target,new Object[]{new SortField[]{sortField}});
//			query.setSortFields(new SortField[]{sortField});
//		}
		
		
		//处理其他属性
		WebDataBinder binder = binderFactory.createBinder(webRequest, target, attributeName);
		ServletRequestDataBinder servletBinder = (ServletRequestDataBinder) binder;
        servletBinder.bind((ServletRequest) webRequest.getNativeRequest());
        
        query.setCount(0);
        query.setFirstResult(0);
        query.setMaxPage(0);
        query.setMinPage(1);
        query.setCount(0);
        if(annot.pageLimitSize() > 0 && query.getPageSize() > annot.pageLimitSize()){
//			Method pageSizeMethod = parameter.getParameterType().getMethod("setPageSize", int.class);
//			ReflectionUtils.invokeMethod(pageSizeMethod, target,new Object[]{annot.pageSize()});
			query.setPageSize(annot.pageLimitSize());
		}
        
     // Add resolved attribute and BindingResult at the end of the model
        Map<String, Object> bindingResultModel = binder.getBindingResult().getModel();
		mavContainer.removeAttributes(bindingResultModel);
		mavContainer.addAllAttributes(bindingResultModel);
        
		return target;
	}

}
