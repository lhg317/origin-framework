package com.goldgov.origin.core.service.proxy;

import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.annotation.AnnotationUtils;

public class ProxyServiceScanner extends ClassPathBeanDefinitionScanner {

	public ProxyServiceScanner(BeanDefinitionRegistry registry) {
		super(registry, false);
	}

	@Override
	public Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
		GenericBeanDefinition definition;
		for (BeanDefinitionHolder holder : beanDefinitions) {
			definition = (GenericBeanDefinition) holder.getBeanDefinition();
			Class<?> serviceIface = null;
			try {
				serviceIface = Class.forName(definition.getBeanClassName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ProxyService proxyService = AnnotationUtils.findAnnotation(serviceIface, ProxyService.class);
			definition.getConstructorArgumentValues().addGenericArgumentValue(proxyService.daoBeanName());
			definition.getConstructorArgumentValues().addGenericArgumentValue(serviceIface);
//			definition.getConstructorArgumentValues()
//					.addGenericArgumentValue(new RuntimeBeanReference(proxyService.daoBeanName()));
			definition.setBeanClass(ProxyServiceObject.class);
			definition.setDependsOn(proxyService.daoBeanName());
		}
		return beanDefinitions;
	}

	
	//默认Spring不会对接口进行Bean定义，因此需要重写此处的代码，允许接口。因为在后续会手工创建Bean定义
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
	}

}
