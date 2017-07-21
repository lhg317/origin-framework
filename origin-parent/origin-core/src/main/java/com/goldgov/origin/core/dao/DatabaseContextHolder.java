package com.goldgov.origin.core.dao;

public class DatabaseContextHolder {
	
	public static final String DEFAULT_DATA_SOURCE_NAME = "dataSource0";
	public static final String DATA_SOURCE_PREFIX = "dataSource";

	public static ThreadLocal<String> dataSourceName = new ThreadLocal<String>(){
		public String initialValue() {
			return DEFAULT_DATA_SOURCE_NAME;
		}
	};
	
	public static void switchDataSource(String name){
		dataSourceName.set(name);
	}
	
	public static String currentDataSourceName(){
		return dataSourceName.get();
	}

}
