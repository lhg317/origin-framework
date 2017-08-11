package com.goldgov.origin.core.service.proxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProxyService {

	public String daoBeanName();
	
	/**
	 * Service方法与Dao方法映射，方法名以“:”分隔，不用定义参数或返回值<br>
	 * 例如：
	 * new String[]{"saveData:addData","updateData:editData"}
	 * @return
	 */
	public String[] methodNameMapping();
	
	
	
}
