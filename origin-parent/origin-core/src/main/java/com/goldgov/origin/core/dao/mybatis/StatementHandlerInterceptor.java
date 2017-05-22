package com.goldgov.origin.core.dao.mybatis;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class,Integer.class})})
public class StatementHandlerInterceptor implements Interceptor{
	
	private List<InterceptorHandler> handlers;
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
		BoundSql boundSql = statementHandler.getBoundSql();
		Connection connection = (Connection)(invocation.getArgs()[0]);
		
		if(handlers != null){
			for (InterceptorHandler interceptorHandler : handlers) {
				if(interceptorHandler.supports(boundSql)){
					interceptorHandler.doHandle(boundSql, metaObject, connection);
				}
			}
		}
		
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}

	public void setHandlers(List<InterceptorHandler> handlers) {
		this.handlers = handlers;
	}

}
