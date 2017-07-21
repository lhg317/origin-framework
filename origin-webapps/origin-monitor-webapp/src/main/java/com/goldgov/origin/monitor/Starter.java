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
    
//    @Bean("dataSource")
//    public RoutingDataSource routingDataSource(List<DataSource> dataSources){
//    	RoutingDataSource routingDataSource = new RoutingDataSource(dataSources);
//    	return routingDataSource;
//    }
//
//    @Bean(name="dataSource1")  
//	@ConfigurationProperties(prefix="spring.datasource")  
//	public DataSource dataSource(){
//		return DataSourceBuilder.create().build();//此类只支持部分数据源DataSourceBuilder.DATA_SOURCE_TYPE_NAMES来查看
//	}
}
