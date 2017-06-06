package com.goldgov.origin.core.utils;

/**
 * 数组工具类
 * @author LiuHG
 * @version 1.0
 */
public abstract class ArrayUtils {

	private ArrayUtils(){}
	
	/**
	 * 判断制定对象是否包含在数组中，返回对象在数组中的索引位置，如果不包含则返回-1
	 * @param arrays
	 * @param obj
	 * @return 返回对象在数组中的索引位置，如果不包含则返回-1
	 */
	public static int contain(Object[] arrays , Object obj){
		for (int i = 0; i < arrays.length; i++) {
			if(arrays[i].equals(obj)){
				return i;
			}
		}
		return -1;
	}
}
