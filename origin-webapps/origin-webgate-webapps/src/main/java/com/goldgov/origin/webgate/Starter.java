package com.goldgov.origin.webgate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.goldgov.origin.core.OriginApplication;
import com.goldgov.origin.core.discovery.IsWebGate;
import com.goldgov.origin.core.discovery.rpc.EnableRpcConfiguration;


/**
 * 如果部署到Weblogic，你的启动类必须直接实现WebApplicationInitializer，即使OriginApplication已经间接实现了此接口
 * @author LiuHG
 * @version 1.0
 */
@SpringBootApplication
@EnableRpcConfiguration
@ComponentScan({"com/goldgov/origin"})
public class Starter extends OriginApplication implements IsWebGate{
	
    public static void main( String[] args ) {
    	SpringApplication.run(new Object[]{Starter.class}, args);
    }

}
