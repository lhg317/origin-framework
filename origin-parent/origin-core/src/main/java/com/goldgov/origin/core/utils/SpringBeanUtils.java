package com.goldgov.origin.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component("SpringBeanUtils")
@SuppressWarnings("unchecked")
public class SpringBeanUtils implements ApplicationContextAware{

	private static ApplicationContext cxt = null;

	
	public static <B> B getBean(String id) {
		try {
			return (B)cxt.getBean(id);
		} catch (NullPointerException e) {
			throw new RuntimeException("当前对象未实例化，请不要在Spring的Bean中声明全局变量以SpringBeanUtils.getBean(String)方式获取实例，可考虑使用注入方式。", e);
		}
	}
	
	public static <B> B getBean(Class<B> requiredType) {
		   return (B)cxt.getBean(requiredType);
	}
	
	public void setApplicationContext(ApplicationContext applicationContext)
	    throws BeansException {
	   cxt = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {
	   return cxt;
	}

}
