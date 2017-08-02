package com.goldgov.origin.server;

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

}
