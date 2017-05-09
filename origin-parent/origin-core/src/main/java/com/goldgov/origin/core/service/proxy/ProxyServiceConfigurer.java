package com.goldgov.origin.core.service.proxy;

import java.util.ResourceBundle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

public class ProxyServiceConfigurer implements BeanDefinitionRegistryPostProcessor,ApplicationContextAware{

	private ApplicationContext applicationContext;
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0) throws BeansException {
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		ProxyServiceScanner scanner = new ProxyServiceScanner(registry);
		scanner.setResourceLoader(this.applicationContext);
		scanner.addIncludeFilter(new AnnotationTypeFilter(ProxyService.class));
		
		//FIXME basePackage
		ResourceBundle bundle = ResourceBundle.getBundle("application");
		scanner.scan(StringUtils.tokenizeToStringArray(bundle.getString("scan-base-package"), ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
	}

}
