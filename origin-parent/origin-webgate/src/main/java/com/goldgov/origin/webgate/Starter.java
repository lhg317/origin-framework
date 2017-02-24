package com.goldgov.origin.webgate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;

import com.goldgov.origin.core.dao.MyBatisConfiguration;
import com.goldgov.origin.core.discovery.rpc.EnableRpcConfiguration;

@SpringBootApplication
@EnableRpcConfiguration
@ComponentScan("com/goldgov/origin")
public class Starter extends SpringBootServletInitializer implements WebApplicationInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(new Class[]{Starter.class,MyBatisConfiguration.class});
	}
	
    public static void main( String[] args ) {
    	SpringApplication.run(new Object[]{Starter.class,MyBatisConfiguration.class}, args);
    }

}
