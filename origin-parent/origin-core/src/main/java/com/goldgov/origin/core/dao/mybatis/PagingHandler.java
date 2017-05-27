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

/**
 * MyBatis分页拦截器处理器，当查询操作且查询方法参数中含有{@link com.goldgov.origin.core.service.Query Query}
 * 对象时，在执行查询前会将查询语句包装成count形式，然后查询出满足条件的总数，并进行页码计算，将页码结果信息赋值回{@link com.goldgov.origin.core.service.Query Query}
 * 对象中，随后根据{@link com.goldgov.origin.core.service.Query Query}中的分页信息对查询语句包装为限制条数的SQL语句，
 * 最后进行真正的数据查询。
 * @author LiuHG
 * @version 1.0
 */
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class PagingHandler implements InterceptorHandler{

	private DatabaseDialect currentDbDialect;
	
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
			String countSql = "select count(*) from (" + sql + ") _t";
			PreparedStatement countStatement = connection.prepareStatement(countSql);
			ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			ResultSet executeQuery = countStatement.executeQuery();
			
			if(executeQuery.next()){
				query.calculate(executeQuery.getLong(1));
			}
			
			if(currentDbDialect == null){
				String dbName = connection.getMetaData().getDatabaseProductName();
				
				DatabaseDialect[] values = DatabaseDialect.values();
				for (DatabaseDialect databaseDialect : values) {
					if(databaseDialect.getProductName().equals(dbName)){
						currentDbDialect = databaseDialect;
						break;
					}
				}
				if(currentDbDialect == null){
					throw new RuntimeException("不支持的数据库类型：" + dbName);
				}
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
