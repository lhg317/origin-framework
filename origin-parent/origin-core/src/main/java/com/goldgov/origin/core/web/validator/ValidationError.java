package com.goldgov.origin.core.web.validator;

public class ValidationError {

	private String fieldName;
	private String message;
	
	public ValidationError(){}
	
	public ValidationError(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return message;
	}
	
}
