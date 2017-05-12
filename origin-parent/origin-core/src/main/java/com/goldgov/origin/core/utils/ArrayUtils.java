package com.goldgov.origin.core.utils;

public abstract class ArrayUtils {

	public static int contain(Object[] arrays , Object obj){
		for (int i = 0; i < arrays.length; i++) {
			if(arrays[i].equals(obj)){
				return i;
			}
		}
		return -1;
	}
}
