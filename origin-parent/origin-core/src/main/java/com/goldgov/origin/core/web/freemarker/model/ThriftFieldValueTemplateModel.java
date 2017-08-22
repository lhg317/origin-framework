package com.goldgov.origin.core.web.freemarker.model;

import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.thrift.TBase;
import org.apache.thrift.TFieldIdEnum;
import org.springframework.util.Assert;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class ThriftFieldValueTemplateModel implements TemplateDirectiveModel{
	
	private DefaultObjectWrapper objectWrapper = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25).build();

	private SimpleDateFormat dateFormat = new SimpleDateFormat();
	private DecimalFormat numberFormat = new DecimalFormat();
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		Object fieldNameModel = params.get("name");
		Object rpcObjModel = params.get("obj");
		Object formatModel = params.get("format");
		
		Assert.notNull(fieldNameModel,"@out name属性不能为空，请检查是否设置了name属性，且为字符串类型（属性值以\"\"包起来）。");
		Assert.notNull(rpcObjModel,"@out obj属性不能为空，且必须为一个Thrift的RPC对象");
		
		String formatPattern = formatModel != null ?formatModel.toString():null;
		
		TBase rpcObj = (TBase)objectWrapper.unwrap((TemplateModel) rpcObjModel);
		String fieldName = fieldNameModel.toString();
		
		short index = 1;
		TFieldIdEnum fieldEnum = rpcObj.fieldForId(index);
		while(fieldEnum != null){
			if(fieldName.equals(fieldEnum.getFieldName())){
				break;
			}
			index++;
			fieldEnum = rpcObj.fieldForId(index);
		}
		if(fieldEnum == null){
			throw new RuntimeException(rpcObj.getClass() + ".fieldEnum == null");
		}
		
		Writer out = env.getOut();
		if(rpcObj.isSet(fieldEnum)){
			Object fieldValue = rpcObj.getFieldValue(fieldEnum);
			String valueStr = null;
			if(fieldValue != null && formatPattern != null){
				if(formatPattern.startsWith("number:")){
					numberFormat.applyPattern(formatPattern.substring(7));
					valueStr = numberFormat.format(fieldValue);
				}else if(formatPattern.startsWith("date:")){
					dateFormat.applyPattern(formatPattern.substring(5));
					valueStr = dateFormat.format(fieldValue);
				}else{
					valueStr = fieldValue.toString();
				}
			}
			if(body != null){
				if(valueStr != null){
					env.setVariable("val", objectWrapper.wrap(valueStr));
				}
				body.render(out);
			}else{
				if(valueStr != null){
					out.write(valueStr);
				}
			}
		}
	}
	
}
