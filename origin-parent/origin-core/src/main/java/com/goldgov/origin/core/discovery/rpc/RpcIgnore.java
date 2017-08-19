package com.goldgov.origin.core.discovery.rpc;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 如果某个服务接口为其他接口的父类，即并不需要进行依赖性配置，则使用该注解进行标注，
 * 被标注的Rpc接口不会在服务注册中心看到，即被被忽略。
 * @author LiuHG
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RpcIgnore {

}
