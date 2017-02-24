package com.goldgov.origin.core.dao.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.goldgov.origin.core.dao.DatabaseDialect;
import com.goldgov.origin.core.service.Query;

@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class,Integer.class})})
public class PagingInterceptor implements Interceptor{

	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
		MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
		String id = mappedStatement.getId();
		if(id.matches(".+ByPage$")){
			BoundSql boundSql = statementHandler.getBoundSql();
			String sql = boundSql.getSql();
			String countSql = "select count(*) from (" + sql + ") t";
			Connection connection = (Connection)(invocation.getArgs()[0]);
			PreparedStatement countStatement = connection.prepareStatement(countSql);
			ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			ResultSet executeQuery = countStatement.executeQuery();
			
			Object parameterObject = boundSql.getParameterObject();
			
			Query query = null;
			if(parameterObject instanceof Query){
				query = (Query)parameterObject;
			}else if(parameterObject instanceof Map){
				Map<String,Object> parameter = (Map<String,Object>)parameterObject;
				Collection<Object> values = parameter.values();
				for (Object object : values) {
					if(object instanceof Query){
						query = (Query)object;
						break;
					}
				}
			}else{
				throw new RuntimeException("当前查询方法参数中没有"+Query.class.getName()+"对象。");
			}
			
			if(executeQuery.next()){
				query.calculate(executeQuery.getLong(1));
			}
			
			String dbName = connection.getMetaData().getDatabaseProductName();
			
			DatabaseDialect[] values = DatabaseDialect.values();
			DatabaseDialect currentDbDialect = null;
			for (DatabaseDialect databaseDialect : values) {
				if(databaseDialect.getProductName().equals(dbName)){
					currentDbDialect = databaseDialect;
					break;
				}
			}
			if(currentDbDialect == null){
				throw new RuntimeException("不支持的数据库类型：" + dbName);
			}
			String pagingSql = currentDbDialect.pagingSql(sql, query.getFirstResult(), query.getPageSize());
			metaObject.setValue("delegate.boundSql.sql", pagingSql);
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

}
