package com.goldgov.origin.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;

import com.goldgov.origin.core.discovery.rpc.EnableRpcConfiguration;

@SpringBootApplication
@EnableRpcConfiguration
@ComponentScan("com/goldgov/origin")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class Starter extends SpringBootServletInitializer implements WebApplicationInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(new Class[]{Starter.class});
	}
	
    public static void main( String[] args ) {
    	SpringApplication.run(new Object[]{Starter.class}, args);
    }

}
