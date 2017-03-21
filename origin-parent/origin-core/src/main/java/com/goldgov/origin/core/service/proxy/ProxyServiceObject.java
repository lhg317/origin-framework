package com.goldgov.origin.core.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.FactoryBean;

@SuppressWarnings("rawtypes")
public class ProxyServiceObject implements FactoryBean{

	private Class serviceIface;
	
	private ProxyServiceImpl proxyServiceImpl;

	public ProxyServiceObject(Class serviceIface,Object daoBean){
		this.serviceIface = serviceIface;
		proxyServiceImpl = new ProxyServiceImpl(daoBean);
	}
	
	@Override
	public Object getObject() throws Exception {
		return Proxy.newProxyInstance(serviceIface.getClassLoader(),serviceIface.getInterfaces(),proxyServiceImpl);
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

}
