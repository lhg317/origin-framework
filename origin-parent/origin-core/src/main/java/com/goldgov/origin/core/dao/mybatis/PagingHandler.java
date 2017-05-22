package com.goldgov.origin.core.dao.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.goldgov.origin.core.dao.DatabaseDialect;
import com.goldgov.origin.core.service.Query;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class PagingHandler implements InterceptorHandler{

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doHandle(BoundSql boundSql, MetaObject metaObject, Connection connection) throws Throwable{
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
		}
		
		if(query != null){
			String sql = boundSql.getSql();
			String countSql = "select count(*) from (" + sql + ") t";
			PreparedStatement countStatement = connection.prepareStatement(countSql);
			ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			ResultSet executeQuery = countStatement.executeQuery();
			
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
	}

	@Override
	public boolean supports(BoundSql boundSql) throws Throwable{
		String sql = boundSql.getSql();
		return sql.toUpperCase().startsWith("SELECT");
	}

}
