package com.goldgov.origin.core.dao.mybatis;

import java.sql.Connection;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.reflection.MetaObject;

/**
 * MyBatis的拦截器处理器
 * @author LiuHG
 * @version 1.0
 */
public interface InterceptorHandler {

	void doHandle(BoundSql boundSql,MetaObject metaObject,Connection connection) throws Throwable;
	
	boolean supports(BoundSql boundSql) throws Throwable;
	
}
