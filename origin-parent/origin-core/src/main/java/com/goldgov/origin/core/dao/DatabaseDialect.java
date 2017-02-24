package com.goldgov.origin.core.dao;


/**
 * 支持的数据库，同时提供数据库之间SQL拼写差异的工具方法。
 * @author LiuHG
 * @version 1.0
 */
//部分可参考org.springframework.boot.jdbc.DatabaseDriver
public enum DatabaseDialect {

	MYSQL("MySQL"),ORACLE("Oracle"),DB2("DB2"),MSSQL("SQL SERVER"),DM("DM");
	
	
	private final String productName;
//	private final String validationQuery;
//	private final String pagingSql;
//	private final String checkTable;
	
	DatabaseDialect(String productName){
		this.productName = productName;
	}
	
	public String getProductName() {
		return productName;
	}

	/**
	 * 根据当前数据库类型，返回检测指定数据表是否存在的SQL语句
	 * @param tableName 指定要检测的表明
	 * @return 根据数据库特点返回的数据表检测SQL语句
	 */
	public String checkTable(String tableName){
		switch (this) {
		case MYSQL:
			return "show tables like '"+tableName+"'";
		case ORACLE:
			return "select 1 from user_tables where table_name='" + tableName + "'";
		case DB2:
			throw new RuntimeException("表存在检查失败，不支持的数据库类型：" + this.name());
		case DM:
			return "select 1 from user_tables where table_name='" + tableName + "'";
		default:
			throw new RuntimeException("表存在检查失败，不支持的数据库类型：" + this.name());
		}
	}
	
	/**
	 * 根据当前数据库类型，返回查询条数限制的SQL查询语句（一般用于分页查询）
	 * @param sql 原始查询SQL
	 * @param first 预查询的第一条记录的索引
	 * @param maxRows 最多返回的数据量
	 * @return 根据数据库特点返回的限制条数的SQL查询语句
	 */
	public String pagingSql(String sql, int first, int maxRows){
		switch (this) {
		case MYSQL:{
			if(first <= 0 && maxRows <= 0){
				return sql;
			}
			return sql + " limit " + first + ","+ maxRows;
		}
		case ORACLE:{
			String sql_ = " select * from (select t.*, rownum rnum from (" + sql + ") t ";
			if (maxRows >= 0) {
				sql_ += " where rownum <= " + (first + maxRows) + ") ";
			}
			if (first >= 0) {
				sql_ += " where rnum >= " +  (first + 1);
			}
			return sql_;
		}
		case DM:{
			String sql_ = " select * from (select t.*, rownum rnum from (" + sql + ") t ";
			if (maxRows >= 0) {
				sql_ += " where rownum <= " + (first + maxRows) + ") ";
			}
			if (first >= 0) {
				sql_ += " where rnum >= " +  (first + 1);
			}
			return sql_;
		}
		case DB2:{
			if (first <= 0 && maxRows <= 0) {
				return sql;
			}
			String sql_ = "select t.* from (select t.*,rownumber() over() as rn from (" + sql + ") t ) t" +
					" where t.rn between "+ (first + 1) +" and  " + (first + maxRows);
			return sql_;
		}
		default:
			throw new RuntimeException("无法完成分页查询：不支持的数据库类型：" + this.name());
		}
	}
}
