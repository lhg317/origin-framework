package com.goldgov.origin.monitor;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.WebApplicationInitializer;

import com.goldgov.origin.core.OriginApplication;
import com.goldgov.origin.core.discovery.rpc.RpcAutoConfiguration;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,RpcAutoConfiguration.class})
public class Application extends OriginApplication implements WebApplicationInitializer{
	
    public static void main( String[] args ) {
    	Application.startup(Application.class, args);
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
