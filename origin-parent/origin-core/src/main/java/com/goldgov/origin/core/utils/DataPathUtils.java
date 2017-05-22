package com.goldgov.origin.core.utils;

public abstract class DataPathUtils {
	
	public static final char PATH_SEPARATOR = '/';

	public static String appendPath(String dataPath,String data){
		if(dataPath.length() == 0 && data.length() == 0){
			return "" + PATH_SEPARATOR;
		}
		
		String[] dataPathSplit = dataPath.split("["+PATH_SEPARATOR+"]");
		String[] resultArray = new String[dataPathSplit.length + 1];
		System.arraycopy(dataPathSplit, 0,resultArray , 0, dataPathSplit.length);
		
		if(data.length() > 0 && data.charAt(0) == PATH_SEPARATOR){
			data = data.substring(1);
		}
		
		resultArray[resultArray.length - 1] = data;
		
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < resultArray.length; i++) {
			if(!"".equals(resultArray[i])){
				strBuilder.append(PATH_SEPARATOR);
				strBuilder.append(resultArray[i]);
			}
		}
		return strBuilder.toString();
	}
	
//	public static void main(String[] args) {
//		System.out.println(DataPathUtils.appendPath("/a/b/c/d", "/e"));
//		System.out.println(DataPathUtils.appendPath("a/b/c/d/", "/e"));
//		System.out.println(DataPathUtils.appendPath("/a/b/c/d/", "e"));
//		System.out.println(DataPathUtils.appendPath("a/b/c/d", "e"));
//		System.out.println(DataPathUtils.appendPath("a/b/c/d", "e/f"));
//		System.out.println(DataPathUtils.appendPath("a/b/c/d", "/e/f"));
//		System.out.println(DataPathUtils.appendPath("a/b/c/d/", "/e/f"));
//		System.out.println(DataPathUtils.appendPath("", "e"));
//		System.out.println(DataPathUtils.appendPath("a/b/c/d", ""));
//		System.out.println(DataPathUtils.appendPath("", ""));
//	}
}
