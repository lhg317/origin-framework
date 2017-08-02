package com.goldgov.origin.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.goldgov.origin"})
public abstract class OriginApplication extends SpringBootServletInitializer{

	/**
	 * 用于扩展添加多个配置类，覆盖本方法时，不用考虑当前类加入进配置类中，默认是将本类作为配置类强制加入的。
	 * 该方法默认什么也不做。
	 * @return 扩展的配置类
	 */
	protected Class<?>[] addConfigure(){
		return null;
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(configure());
	}
	
	private Class<?>[] configure(){
		List<Class<?>> configs = new ArrayList<>();
		configs.add(getClass());
		if(addConfigure() != null){
			configs.addAll(Arrays.asList(addConfigure()));
		}
		return configs.toArray(new Class<?>[0]);
	}
}
