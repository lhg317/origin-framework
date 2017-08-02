package com.goldgov.origin.core.discovery.rpc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public interface ObjectConverter<T,R> {

	public static final String[] PK_ARRAY_TYPE = new String[0];
	
	public R toRpcObject(T obj);
	public T fromRpcObject(R rpcObj);
	
	public static class Utils{
		public static String[] listToArray(List<String> list){
			return list.toArray(PK_ARRAY_TYPE);
		}
		
		public static List<String> arrayToList(String[] array){
			return Arrays.asList(array);
		}
		
		public static long dateToLong(Date date){
			return date.getTime();
		}
		
		public static Date longToDate(long dateLong){
			return new Date(dateLong);
		}
	}
}
