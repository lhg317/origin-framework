package com.goldgov.origin.core.dao.mybatis;

import java.sql.Connection;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.reflection.MetaObject;

public interface InterceptorHandler {

	void doHandle(BoundSql boundSql,MetaObject metaObject,Connection connection) throws Throwable;
	
	public boolean supports(BoundSql boundSql) throws Throwable;
}
