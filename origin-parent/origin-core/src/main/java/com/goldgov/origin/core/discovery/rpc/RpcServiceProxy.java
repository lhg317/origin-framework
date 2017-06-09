package com.goldgov.origin.core.discovery.rpc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SuppressWarnings("rawtypes")
public class RpcServiceProxy implements FactoryBean,ApplicationContextAware{

	private Class rpcClass;
	
	private String serviceName;
	
	private String displayName;
	
	private Object rpcService;

	private ApplicationContext applicationContext;
	
	public RpcServiceProxy(Class rpcClass,ServiceNameGenerator serviceNameGenerator){
		this.rpcClass = rpcClass;
		Class<?>[] interfaces = rpcClass.getInterfaces();
		for (Class<?> iface : interfaces) {
			if(iface.getSimpleName().equals("Iface")){
				Class<?> declaringClass = iface.getDeclaringClass();
				
				if(rpcClass == null) continue;
				serviceName = serviceNameGenerator.generateServiceName(declaringClass);
				break;
			}
		}
		
		@SuppressWarnings("unchecked")
		RpcService rpcService = (RpcService) rpcClass.getAnnotation(RpcService.class);
		if(!"".equals(rpcService.displayName())){
			displayName = rpcService.displayName();
		}else{
			displayName = serviceName;
		}
	}
	
	@Override
	public Object getObject() throws Exception {
		if(rpcService == null){
			rpcService = rpcClass.newInstance();
			AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
			beanFactory.autowireBeanProperties(rpcService, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
		}
		return rpcService;
	}

	@Override
	public Class getObjectType() {
		return rpcClass;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getDisplayName() {
		return displayName;
	}

	@Override
	public String toString() {
		return "rpc class:" + rpcClass + ",service name:" + serviceName;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
}
