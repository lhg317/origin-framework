package com.goldgov.origin.core.web.validator.impl;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goldgov.origin.core.web.annotation.OperateType;
import com.goldgov.origin.core.web.validator.ConstraintValidator;
import com.goldgov.origin.core.web.validator.annotation.NotContain;

public class NotContainValidator implements ConstraintValidator<NotContain,String>{

//	private static Map<String,List<String>> wordMap = new HashMap<String,List<String>>();
	
	private OperateType[] types;
	private String[] words;
	
	@Override
	public void initialize(NotContain constraintAnnotation) {
		types = constraintAnnotation.type();
		words = constraintAnnotation.words();
	}

	@Override
	public boolean isValid(String name, String value, Field field,
			OperateType type, HttpServletRequest request,
			HttpServletResponse response) {
		if(Utils.operatingValidate(type, types)){
			for (String word : words) {
//				if(word.startsWith("file:")){
//					String file = word.substring(5);
//					file = PuzzleContext.getWebRoot() + (file.startsWith(File.separator) ? file.substring(1) : file);
//					
//					List<String> wordList = null; 
//							
//					if(wordMap.containsKey(file)){
//						wordList = wordMap.get(file);
//					}else{
//						BufferedReader reader = null;
//						wordList = new ArrayList<String>();
//						try {
//							reader = new BufferedReader(new FileReader(file));
//							String str = null;
//							while((str = reader.readLine()) != null){
//								wordList.add(str);
//							}
//							wordMap.put(file, wordList);
//						} catch (FileNotFoundException e) {
//							throw new RuntimeException("敏感词文件不存在：" + file,e);
//						} catch (IOException e) {
//							throw new RuntimeException("读取敏感词文件时发生IO异常：" + file,e);
//						}finally{
//							if(reader != null){
//								try {
//									reader.close();
//								} catch (IOException e) {
//									throw new RuntimeException("关闭敏感词文件时发生IO异常：" + file,e);
//								}
//							}
//						}
//					}
//					
//					if(wordList.contains(value)){
//						return false;
//					}
//					
//				}else 
				if(word.equals(value)){
					return false;
				}
			}
		}
		return true;
	}

}
