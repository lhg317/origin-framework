package com.goldgov.origin.core.discovery.rpc;

import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.thrift.TServiceClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.StringUtils;


public class RpcScannerConfigurer implements BeanDefinitionRegistryPostProcessor,ApplicationContextAware{

	
	private ApplicationContext applicationContext;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0) throws BeansException {
		
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		RpcDefinitionScanner scanner = new RpcDefinitionScanner(registry);
		scanner.setResourceLoader(this.applicationContext);
		scanner.setBeanNameGenerator(new AnnotationBeanNameGenerator(){
			@Override
			public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
				Class<?> rpcClassClass = null;
				try {
					rpcClassClass = Class.forName(definition.getBeanClassName());
				} catch (ClassNotFoundException e) {
					throw new RuntimeException("class not found",e);
				}
				RpcService annos = AnnotationUtils.findAnnotation(rpcClassClass, RpcService.class);
				if(annos != null && !"".equals(annos.value())){
					return annos.value();
				}else{
					return super.generateBeanName(definition, registry);
				}
			}
		});
//		System.out.println(applicationContext.getAutowireCapableBeanFactory().autowireBean(existingBean));
		scanner.addIncludeFilter(new AnnotationTypeFilter(RpcService.class));
		scanner.addIncludeFilter(new AssignableTypeFilter(TServiceClient.class));
		scanner.addExcludeFilter(new TypeFilter(){
			@Override
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
					throws IOException {
				ClassMetadata metadata = metadataReader.getClassMetadata();
				try {
					Class<?> forNameClass = Class.forName(metadata.getClassName());
					
					RpcIgnore rpcIgnore = forNameClass.getAnnotation(RpcIgnore.class);
					Class<?> declaringClass = forNameClass.getDeclaringClass();
					if(rpcIgnore == null && declaringClass != null){
						rpcIgnore = declaringClass.getAnnotation(RpcIgnore.class);
					}
					return rpcIgnore != null;
				} catch (Throwable e) {
					// Class not regularly loadable - can't determine a match that way.
				}
				return false;
			}});
		
		//FIXME basePackage
		ResourceBundle bundle = ResourceBundle.getBundle("application");
		scanner.scan(StringUtils.tokenizeToStringArray(bundle.getString("rpc.server.base-package"), ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}


	
	

}
