package com.goldgov.origin.core.discovery.rpc;

import java.util.ArrayList;
import java.util.List;

public final class ResultSetUtils {
	
	private ResultSetUtils(){}

	public static <T, R> List<T> convertFormRpc(List<R> rpclist,ObjectConverter<T,R> converter){
		ArrayList<T> resultList = new ArrayList<>();
		for (R r : rpclist) {
			resultList.add(converter.fromRpcObject(r));
		}
		return resultList;
	}
	
	public static <T, R> List<R> convertToRpc(List<T> list,ObjectConverter<T,R> converter){
		ArrayList<R> resultList = new ArrayList<>();
		for (T r : list) {
			resultList.add(converter.toRpcObject(r));
		}
		return resultList;
	}
	
}
