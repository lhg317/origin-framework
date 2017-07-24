package com.goldgov.origin.core.dao;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.scripting.xmltags.XMLScriptBuilder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.Alias;

//可用此方法动态读写分离，通过MyBatisConfiguration中的SqlSessionFactoryBean.setTypeAliases设置本类
//-NOT USE-
@Alias("lhg")
public class MyLanguageDriver implements LanguageDriver{

	//每次执行都会调用
	@Override
	public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject,
			BoundSql boundSql) {
		//可用此方法动态读写分离
//		DatabaseContextHolder.switchDataSource(name);
		 return new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
	}

	//首次读取配置文件构造，以后不再读取
	@Override
	public SqlSource createSqlSource(Configuration configuration, XNode script, Class<?> parameterType) {
		 XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
		    return builder.parseScriptNode();
	}

	//只有通过注解方式配置SQL，才会用到此类
	@Override
	public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
		// TODO Auto-generated method stub
		return null;
	}

}
