package com.goldgov.origin.modules.organization;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Date;

import org.springframework.cglib.core.ReflectUtils;

import com.goldgov.origin.modules.organization.service.OrganizationUser;

@SuppressWarnings("rawtypes")
public class MyBatisXmlUtils {
	
	public static final String RESULT_MAP_BEGIN = "<resultMap type=\"{0}\" id=\"{1}\">";
	public static final String RESULT_MAP_END = "</resultMap>";
	//{0} 数据库字段名 {1}属性名  {2}JDBC类型
	public static final String RESULT_MAP_ID = "\t<id column=\"{0}\" jdbcType=\"{2}\" property=\"{1}\"/>";
	public static final String RESULT_MAP_RESULT = "\t<result column=\"{0}\" jdbcType=\"{2}\" property=\"{1}\"/>";

	public void resultMap(Class clazz,OutputStream out){
		PropertyDescriptor[] beanProperties = ReflectUtils.getBeanProperties(clazz);
		OutputStreamWriter writer = new OutputStreamWriter(out);
		writeLine(writer,MessageFormat.format(RESULT_MAP_BEGIN, clazz.getName(),lowerCaseFirst(clazz.getSimpleName())));
		for (int i = 0; i < beanProperties.length; i++) {
			Method readMethod = beanProperties[i].getReadMethod();
			if(readMethod != null){
				if(beanProperties[i].getName().equalsIgnoreCase("entityID")){
					writeLine(writer,MessageFormat.format(RESULT_MAP_ID, clazz.getSimpleName().toUpperCase() + "_ID",beanProperties[i].getName(),getJdbcType(readMethod.getReturnType())));
				}else{
					writeLine(writer,MessageFormat.format(RESULT_MAP_RESULT, getFieldName(beanProperties[i].getName()),beanProperties[i].getName(),getJdbcType(readMethod.getReturnType())));
				}
			}
		}
		writeLine(writer,RESULT_MAP_END);
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static final String SQL_BEGIN = "<sql id=\"{0}\">";
	public static final String SQL_END = "</sql>";
	
	public void queryFields(Class clazz,OutputStream out){
		queryFields(null,clazz,out);
	}
	public void queryFields(String id,Class clazz,OutputStream out){
		if(id == null){
			id = lowerCaseFirst(clazz.getSimpleName())+"QueryFields";
		}
		
		PropertyDescriptor[] beanProperties = ReflectUtils.getBeanProperties(clazz);
		OutputStreamWriter writer = new OutputStreamWriter(out);
		writeLine(writer,MessageFormat.format(SQL_BEGIN, id));
		for (int i = 0; i < beanProperties.length; i++) {
			Method readMethod = beanProperties[i].getReadMethod();
			if(readMethod != null){
				String fieldName = null;
				if(beanProperties[i].getName().equalsIgnoreCase("entityID")){
					fieldName = clazz.getSimpleName().toUpperCase() + "_ID";
				}else{
					fieldName = getFieldName(beanProperties[i].getName());
				}
				
				String sqlField = "\t" + fieldName + " as \"" + beanProperties[i].getName() + "\"";
				if(beanProperties.length -1 != i){
					sqlField += ",";
				}
				writeLine(writer,sqlField);
			}
		}
		writeLine(writer,SQL_END);
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertFields(Class clazz,OutputStream out){
		PropertyDescriptor[] beanProperties = ReflectUtils.getBeanProperties(clazz);
		OutputStreamWriter writer = new OutputStreamWriter(out);
		StringBuilder fields = new StringBuilder();
		StringBuilder values = new StringBuilder();
		for (int i = 0; i < beanProperties.length; i++) {
			Method readMethod = beanProperties[i].getReadMethod();
			if(readMethod != null){
				String fieldName = getFieldName(beanProperties[i].getName());;				
				fields.append(fieldName);
				values.append("#{" + beanProperties[i].getName() + "}");
				if(beanProperties.length -1 != i){
					fields.append(",");
					values.append(",");
				}
			}
		}
		writeLine(writer,"(" + fields.toString() + ") VALUES ("+values.toString()+")");
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static final String UPDATE_SET_BEGIN = "<set>";
	public static final String UPDATE_SET = "\t<if test=\"{1} != null \">{0}</if>";
	public static final String UPDATE_SET_END = "</set>";
	public void updateFields(Class clazz,OutputStream out){
		PropertyDescriptor[] beanProperties = ReflectUtils.getBeanProperties(clazz);
		OutputStreamWriter writer = new OutputStreamWriter(out);
		StringBuilder sets = new StringBuilder();
		writeLine(writer,UPDATE_SET_BEGIN);
		for (int i = 0; i < beanProperties.length; i++) {
			Method readMethod = beanProperties[i].getReadMethod();
			if(readMethod != null){
				String fieldName = null;
				if(beanProperties[i].getName().equalsIgnoreCase("entityID")){
					fieldName = clazz.getSimpleName().toUpperCase() + "_ID";
				}else{
					fieldName = getFieldName(beanProperties[i].getName());
				}
				writeLine(writer,MessageFormat.format(UPDATE_SET, fieldName+" = #{" + beanProperties[i].getName() + "},",beanProperties[i].getName()));
//				if(beanProperties.length -1 != i){
//					sets.append(",");
//				}
			}
		}
		writeLine(writer,UPDATE_SET_END);
		writeLine(writer,sets.toString());
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//******************************************************************************************************
	
	public void writeLine(Writer w,String str){
		try {
			w.write(str);
			w.write("\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String lowerCaseFirst(String name){
		char[] charArray = name.toCharArray();
		charArray[0] = Character.toLowerCase(charArray[0]);
		return new String(charArray);
	}
	
	public String getFieldName(String name){
		StringBuilder sb = new StringBuilder();
		
		char[] charArray = name.toCharArray();
		for (char c : charArray) {
			if(Character.isUpperCase(c)){
				sb.append("_");
			}
			sb.append(c);
		}
		String upperCase = sb.toString().toUpperCase();
		if(upperCase.endsWith("_I_D")){
			upperCase = upperCase.substring(0, upperCase.length()-4) + "_ID";
		}
		return upperCase;
	}
	
	public String getJdbcType(Class type){
		if(type == String.class){
			return "VARCHAR";
		}else if(type == Integer.class){
			return "NUMERIC";
		}else if(type == Date.class){
			return "TIMESTAMP";
		}else{
			return "VARCHAR";
		}
	}
	
//	public enum DataTypeMapping{
//		STRING("VARCHAR"),INTEGER("NUMERIC"),DATE("TIMESTAMP");
//		
//		private String jdbcType;
//		
//		private DataTypeMapping(String jdbcType){
//			this.jdbcType = jdbcType;
//		}
//		
//		public String getJdbcType(){
//			return jdbcType;
//		}
//	}
	
	public static void main(String[] args) {//BaseDataLocale
		MyBatisXmlUtils myBatisXmlUtils = new MyBatisXmlUtils();
//		myBatisXmlUtils.resultMap(OrganizationUser.class, System.out);
//		myBatisXmlUtils.queryFields(OrganizationUser.class, System.out);
		myBatisXmlUtils.insertFields(OrganizationUser.class, System.out);
//		myBatisXmlUtils.updateFields(OrganizationUser.class, System.out);
	}
}
