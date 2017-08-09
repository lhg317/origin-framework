package utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.goldgov.origin.modules.auth.api.RpcAuthAccount;

public class ProxyBeanUtils {

	public static void main1111(String[] args) {
//		System.out.println(new ProxyBeanUtils().generateProxyBean(AuthAccount.class));
	}
	
	public ProxyBean generateProxyBean(Class<?> clazz){
		String className = clazz.getName();
		int apiIndex = className.indexOf(".service.");
		String rpcClassName = className.substring(0,apiIndex) + ".api.Rpc" + clazz.getSimpleName();
		Class<?> rpcClass = null;
		try {
			rpcClass = Class.forName(rpcClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("RCP类不存在" + rpcClassName,e);
		}
		String packageName = clazz.getPackage().getName()+".impl";
		
		List<ProxyConstructor> cList= new ArrayList<>();
		cList.add(new ProxyConstructor());
		cList.add(new ProxyConstructor(rpcClass));
		cList.add(new ProxyConstructor(clazz));
		ProxyBean proxyBean = new ProxyBean("Proxy"+clazz.getSimpleName(),cList);
		proxyBean.setPackageName(packageName);
		proxyBean.setParentClass(clazz);
		proxyBean.setProxyBeanClass(rpcClass);
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		proxyBean.addAttrField(lowerFirstChar(rpcClass.getSimpleName()),rpcClass,false,false);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			if(propertyDescriptor.getReadMethod() != null && propertyDescriptor.getWriteMethod() != null){
				proxyBean.addAttrField(propertyDescriptor.getName(), propertyDescriptor.getPropertyType());
			}
		}
		
		return proxyBean;
		
	}
	
	public class ProxyBean{
		
		private String packageName;
		private String className;
		private Class<?> proxyBeanClass;
		private List<Class<?>> importClasses = new ArrayList<>();
		private List<Class<?>> interfaceClasses = new ArrayList<>();
		private Class<?> parentClass;
		private List<AttrField> fields = new ArrayList<>();
		private List<ProxyConstructor> constructors;
		
		public ProxyBean(String name,List<ProxyConstructor> constructors){
			this.className = name;
			this.constructors = constructors;
		}

		public String getPackageName() {
			return packageName;
		}

		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}

		public List<Class<?>> getImportClasses() {
			return importClasses;
		}

		public void setImportClasses(List<Class<?>> importClasses) {
			this.importClasses = importClasses;
		}

		public List<Class<?>> getInterfaceClasses() {
			return interfaceClasses;
		}

		public void addInterface(Class<?> interfaceClass) {
			interfaceClasses.add(interfaceClass);
		}

		public Class<?> getParentClass() {
			return parentClass;
		}

		public void setParentClass(Class<?> parentClass) {
			this.parentClass = parentClass;
		}

		public List<AttrField> getAttrFields() {
			return fields;
		}

		public void addAttrField(AttrField field) {
			fields.add(field);
		}
		public void addAttrField(String name, Class<?> type) {
			addAttrField(new AttrField(name, type));
		}
		
		public void addAttrField(String name, Class<?> type,boolean hasSetter,boolean hasGetter) {
			AttrField attrField = new AttrField(name, type);
			attrField.setHasSetter(hasSetter);
			attrField.setHasGetter(hasGetter);
			addAttrField(attrField);
		}

		public String getClassName() {
			return className;
		}

		public List<ProxyConstructor> getConstructors() {
			return constructors;
		}
		
		public Class<?> getProxyBeanClass() {
			return proxyBeanClass;
		}

		public void setProxyBeanClass(Class<?> proxyBeanClass) {
			this.proxyBeanClass = proxyBeanClass;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(1024);
			sb.append("package " + packageName+ ";");
			sb.append("\r\n");
			sb.append("\r\n");
			for (Class<?> iClass : importClasses) {
				sb.append("import " + iClass.getName() + ";");
				sb.append("\r\n");
			}
			sb.append("public class " + className);
			if(parentClass != null){
				sb.append(" extends " + parentClass.getName());
			}
			
			if(importClasses.size() > 0){
				sb.append(" implements ");
				for (int i = 0; i < importClasses.size(); i++) {
					if(i > 0){
						sb.append(",");
					}
					sb.append(importClasses.get(i).getName());
				}
			}
			sb.append("{\r\n");
			for (AttrField f : fields) {
				sb.append("\tprivate " + f.getType().getName() + " " + f.getName()+";");
				sb.append("\r\n");
			}
			
			for (ProxyConstructor proxyConstructor : constructors) {
				sb.append("\tpublic " + className + "(");
				Class<?>[] args = proxyConstructor.getArgs();
				for (int i = 0; i < args.length; i++) {
					if(i > 0){
						sb.append(",");
					}
					sb.append(args[i].getName() + " " + lowerFirstChar(args[i].getSimpleName()));
				}
				sb.append("){");
				sb.append("\r\n");
				sb.append("\t}");
				sb.append("\r\n");
			}
			
			GetSeter getSeter = new GetSeter(1,RpcAuthAccount.class);//FIXME 
			for (AttrField f : fields) {
				if(f.isHasSetter()){
					sb.append(getSeter.getSetterCode( f.getName(),f.getType()));
					sb.append("\r\n");
				}
				if(f.isHasGetter()){
					sb.append(getSeter.getGetterCode( f.getName(),f.getType()));
					sb.append("\r\n");
				}
			}
			sb.append("}\r\n");
			return sb.toString();
		}
		
	}
	
	public class ProxyConstructor{
		public final Class<?>[] args;
		
		public ProxyConstructor(Class<?>... args){
			this.args = args;
		}

		public Class<?>[] getArgs() {
			return args;
		}
	}
	
	public class AttrField {
		private String name;
		private Class<?> type;
		private boolean hasGetter =  true;
		private boolean hasSetter = true;
		
		public AttrField(String name, Class<?> type) {
			this.name = name;
			this.type = type;
		}
		public String getName() {
			return name;
		}
		public Class<?> getType() {
			return type;
		}
		public boolean isHasGetter() {
			return hasGetter;
		}
		public void setHasGetter(boolean hasGetter) {
			this.hasGetter = hasGetter;
		}
		public boolean isHasSetter() {
			return hasSetter;
		}
		public void setHasSetter(boolean hasSetter) {
			this.hasSetter = hasSetter;
		}
	}
	
	public class GetSeter{
		
		private String indentation;
		private Class<?> delegate;

		public GetSeter(int indeNum){
			this(indeNum,null);
		}
		public GetSeter(int indeNum,Class<?> delegate){
			this.delegate = delegate;
			char[] indChars = new char[indeNum];
			Arrays.fill(indChars, '\t');
			indentation = new String(indChars);
		}
		
		public String getGetterCode(String name,Class<?> clazz){
			StringBuilder sb = new StringBuilder();
			sb.append(indentation + "public " + clazz.getSimpleName() + " ");
			sb.append(getGetMethodName(name, clazz));
			sb.append("() {");
			sb.append("\r\n");
			sb.append(indentation + indentation);
			sb.append("return ");
			if(delegate != null){
				sb.append(lowerFirstChar(delegate.getSimpleName()) + "." + getGetMethodName(name,clazz) + "();");
			}else{
				sb.append(name+";");
			}
			sb.append("\r\n");
			sb.append(indentation + "}");
			sb.append("\r\n");
			return sb.toString();
		}
		
		public String getSetterCode(String name,Class<?> clazz){
			StringBuilder sb = new StringBuilder();
			sb.append(indentation + "public void ");
			sb.append(getSetMethodName(name, clazz));
			sb.append("(");
			sb.append(clazz.getSimpleName() + " " + name);
			sb.append(") {");
			sb.append("\r\n");
			if(delegate != null){
				sb.append(indentation + indentation);
				sb.append(lowerFirstChar(delegate.getSimpleName()) + "." + getSetMethodName(name,clazz) + "(" + name + ");");
			}else{
				sb.append("this." + name + "=" +name+";");
			}
			sb.append(indentation + "}");
			sb.append("\r\n");
			return sb.toString();
		}
		
		private String getGetMethodName(String name, Class<?> clazz) {
			StringBuilder sb = new StringBuilder();
			if(clazz.isAssignableFrom(Boolean.class) || clazz == Boolean.TYPE){
				if(name.startsWith("is")){
					sb.append(name);
				}else{
					sb.append("is" + upperFirstChar(name));
				}
			}else{
				sb.append("get" + upperFirstChar(name));
			}
			return sb.toString();
		}
		
		private String getSetMethodName(String name, Class<?> clazz) {
			StringBuilder sb = new StringBuilder();
			sb.append("set" + upperFirstChar(name));
			return sb.toString();
		}
		
	}
	
	private String upperFirstChar(String str){
		char[] charArray = str.toCharArray();
		charArray[0] = Character.toUpperCase(charArray[0]);
		return new String(charArray);
	}
	
	private String lowerFirstChar(String str){
		char[] charArray = str.toCharArray();
		charArray[0] = Character.toLowerCase(charArray[0]);
		return new String(charArray);
	}
	
//	public interface CodeWriter{
//		void write();
//	}
//	
//	public interface SetterTypeWriter extends CodeWriter{
//		public boolean supports(Class<?> type);
//	}
//	
//	public interface GetterTypeWriter extends CodeWriter{
//		public boolean supports(Class<?> type);
//	}
	
}
