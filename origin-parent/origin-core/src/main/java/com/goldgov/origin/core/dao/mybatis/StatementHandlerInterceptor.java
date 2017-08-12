package com.goldgov.origin.core.dao.mybatis;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.beans.factory.annotation.Value;

/**
 * MyBatis基于{@link org.apache.ibatis.executor.statement.StatementHandler StatementHandler}接口的拦截器，
 * 拦截接口的prepare方法，在此拦截切点上可以配置多个处理器，处理器需要实现{@link com.goldgov.origin.core.dao.mybatis.InterceptorHandler InterceptorHandler}
 * 接口。
 * 
 * @author LiuHG
 * @version 1.0
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class,Integer.class})})
public class StatementHandlerInterceptor implements Interceptor{
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Value("${showSql:false}")
	private boolean showSql;
	
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
		
		if(showSql){
			String sql = boundSql.getSql();
			sql = sql.replaceAll("\r\n", "");
			if(logger.isDebugEnabled()){
				logger.debug(sql);
			}else{
				System.out.println(sql);
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
