package com.goldgov.origin.core.discovery.rpc;

import java.util.Arrays;
import java.util.Set;

import org.apache.thrift.TServiceClient;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.annotation.AnnotationUtils;

public class RpcDefinitionScanner extends ClassPathBeanDefinitionScanner{

	public RpcDefinitionScanner(BeanDefinitionRegistry registry) {
		super(registry, false);
	}
	
	  @Override
	  public Set<BeanDefinitionHolder> doScan(String... basePackages) {
	    Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

	    if (beanDefinitions.isEmpty()) {
	      logger.warn("No RPC service was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
	    } else {
	      processBeanDefinitions(beanDefinitions);
	    }

	    return beanDefinitions;
	  }

	private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
		 GenericBeanDefinition definition;
		    for (BeanDefinitionHolder holder : beanDefinitions) {
		      definition = (GenericBeanDefinition) holder.getBeanDefinition();
		      Class<?> rpcClass = null;
		      try {
				rpcClass = Class.forName(definition.getBeanClassName());
		      } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		      }
		      
		      if(isServiceClass(rpcClass)){
		    	  processServerDefinitions(definition,rpcClass);
		      }
		      if(isClientClass(rpcClass)){
		    	  processClientDefinitions(definition,rpcClass);
		      }
		      
//		      definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
		    }
	}
	
	protected boolean  isServiceClass(Class<?> rpcClass){
		RpcService annos = AnnotationUtils.findAnnotation(rpcClass, RpcService.class);
		return annos != null;
	}
	
	protected boolean  isClientClass(Class<?> rpcClass){
		return TServiceClient.class.isAssignableFrom(rpcClass);
	}
	
	protected void processServerDefinitions(GenericBeanDefinition definition,Class<?> rpcClass){
		definition.getConstructorArgumentValues().addGenericArgumentValue(rpcClass);
		definition.getConstructorArgumentValues().addGenericArgumentValue(new RuntimeBeanReference("serviceNameGenerator"));
	    definition.setBeanClass(RpcServiceProxy.class);
	    
	}
	
	protected void processClientDefinitions(GenericBeanDefinition definition,Class<?> rpcClass){
		 
	     definition.getConstructorArgumentValues().addGenericArgumentValue(rpcClass);
//	     definition.getConstructorArgumentValues().addGenericArgumentValue(new RuntimeBeanReference("socketProvider"));
	     definition.getConstructorArgumentValues().addGenericArgumentValue(new RuntimeBeanReference("rpcServiceProviderCenter"));
	     definition.getConstructorArgumentValues().addGenericArgumentValue(new RuntimeBeanReference("serviceNameGenerator"));
	     definition.setBeanClass(RpcClientProxy.class);
	     definition.setDependsOn("rpcServer");
	}

}
