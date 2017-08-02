package com.goldgov.origin.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

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
	
	public static void startup(Object[] sources,String[] args){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		SpringApplication.run(sources, args);
		stopWatch.stop();
		printStartInfo(stopWatch);
	}
	
	public static void startup(Object source,String[] args){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		SpringApplication.run(source, args);
		stopWatch.stop();
		printStartInfo(stopWatch);
	}
	
	private static void printStartInfo(StopWatch stopWatch){
		StringBuilder message = new StringBuilder();
		message.append("Started in ");
		message.append(stopWatch.getTotalTimeSeconds());
		message.append(" seconds.");
		message.append(getPid());
		System.out.println(message);
	}
	
	private static String getPid() {
		return getValue(" PID: ", new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				return System.getProperty("PID");
			}
		});
	}
	
	
	private static String getValue(String prefix, Callable<Object> call) {
		return getValue(prefix, call, "");
	}

	private static String getValue(String prefix, Callable<Object> call, String defaultValue) {
		try {
			Object value = call.call();
			if (value != null && StringUtils.hasLength(value.toString())) {
				return prefix + value;
			}
		}
		catch (Exception ex) {
			// Swallow and continue
		}
		return defaultValue;
	}
}
