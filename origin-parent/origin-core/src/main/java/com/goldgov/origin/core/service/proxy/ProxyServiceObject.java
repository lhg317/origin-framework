package com.goldgov.origin.core.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SuppressWarnings("rawtypes")
public class ProxyServiceObject implements FactoryBean,ApplicationContextAware{

	private Class serviceIface;
	
	private ProxyServiceImpl proxyServiceImpl;
	
	private ApplicationContext applicationContext;

	private String beanName;

//	public ProxyServiceObject(Class serviceIface,Object daoBean){
	public ProxyServiceObject(String beanName,Class serviceIface){
		this.beanName = beanName;
		this.serviceIface = serviceIface;
//		proxyServiceImpl = new ProxyServiceImpl(daoBean);
	}
	
	@Override
	public Object getObject() throws Exception {
		Object bean = applicationContext.getBean(beanName);
		proxyServiceImpl = new ProxyServiceImpl(bean);
		Object proxyInstance = Proxy.newProxyInstance(serviceIface.getClassLoader(),new Class[]{serviceIface},proxyServiceImpl);
//		AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
//		beanFactory.autowireBeanProperties(proxyInstance, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
		return proxyInstance;
	}

	@Override
	public Class getObjectType() {
		return serviceIface;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	public static class ProxyServiceImpl implements InvocationHandler{

		private Object daoBean;

		private Map<String,Method> methodMap = new HashMap<>();
		
		public ProxyServiceImpl(Object daoBean){
			this.daoBean = daoBean;
		}
		
		@Override
		public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
			Method method = methodMap.get(arg1.getName());
			if(method == null){
				method = daoBean.getClass().getMethod(arg1.getName(), arg1.getParameterTypes());
				if(method == null){
					throw new RuntimeException("代理service执行失败，与DAO方法不匹配");
				}
				methodMap.put(arg1.getName(), method);
			}
			return method.invoke(daoBean, arg2);
		}
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
