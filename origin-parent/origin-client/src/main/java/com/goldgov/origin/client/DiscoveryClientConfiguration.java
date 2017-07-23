package com.goldgov.origin.client;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@Configurable
@AutoConfigureAfter(EmbeddedServletContainerAutoConfiguration.class)
public class DiscoveryClientConfiguration {

	@Bean
	public LocalServiceRegister discoveryRegister(){
		return new LocalServiceRegister();
	}
	
	@Bean
	public ClientConfig clientConfig(){
		return new ClientConfig();
	}
	
	@Bean
	@ConditionalOnProperty(name="embedded.container",havingValue="false")
	public ServletRegistrationBean discoveryRegisterByWar(LocalServiceRegister discoveryRegister) {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.addUrlMappings("/localServiceRegisterServlet");
		servletRegistrationBean.setLoadOnStartup(1);
		servletRegistrationBean.setServlet(new LocalServiceRegisterServlet(discoveryRegister));
		return servletRegistrationBean;
	}
}
