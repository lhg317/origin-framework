package com.goldgov.origin.core.discovery.rpc;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Rpc接口实现类的标注注解，此时该注解可以代替Sring的@Service注解。但非Rpc实现类的对象不要标注此注解。
 * @author LiuHG
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RpcService {

	String value() default "";
	
	String displayName() default "";
	
}
