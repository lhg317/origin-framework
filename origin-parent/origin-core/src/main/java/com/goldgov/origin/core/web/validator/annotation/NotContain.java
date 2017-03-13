package com.goldgov.origin.core.web.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.goldgov.origin.core.web.annotation.OperateType;
import com.goldgov.origin.core.web.validator.Constraint;
import com.goldgov.origin.core.web.validator.impl.NotContainValidator;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = NotContainValidator.class)
public @interface NotContain {

	public String fieldName() default "";
	
	public String fieldDesc() default "";
	
	public OperateType[] type() default OperateType.NONE;
	
	public String message() default "{fieldDesc}包含敏感词，验证未通过";
	
	/**
	 * 敏感词定义，如果敏感词以“file:”开头，表示引用以应用为根的文本文件，以每个敏感词占一行，注：路径以系统路径分隔符分隔，即：<code>File.separator</code>
	 * @return
	 */
	public String[] words();
}
