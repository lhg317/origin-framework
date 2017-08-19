package com.goldgov.origin.core.discovery.rpc;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询结果集与Ppc结果集转换工具类，
 * 转换时需要给定一个{@link com.goldgov.origin.core.discovery.rpc.ObjectConverter ObjectConverter}实现类。
 * @author LiuHG
 * @version 1.0
 */
public final class ResultSetUtils {
	
	private ResultSetUtils(){}

	/**
	 * 将Rpc结果集转换为业务对象集合
	 * @param rpclist
	 * @param converter
	 * @return
	 */
	public static <T, R> List<T> convertFormRpc(List<R> rpclist,ObjectConverter<T,R> converter){
		ArrayList<T> resultList = new ArrayList<>();
		for (R r : rpclist) {
			resultList.add(converter.fromRpcObject(r));
		}
		return resultList;
	}
	
	/**
	 * 将业务对象集合转换为Rpc结果集
	 * @param list
	 * @param converter
	 * @return
	 */
	public static <T, R> List<R> convertToRpc(List<T> list,ObjectConverter<T,R> converter){
		ArrayList<R> resultList = new ArrayList<>();
		for (T r : list) {
			resultList.add(converter.toRpcObject(r));
		}
		return resultList;
	}
	
}
