package com.goldgov.origin.core.web.validator;

import java.util.List;



public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = -5404019679192897829L;

	public List<ValidationError> errors;
	
	public ValidationException(List<ValidationError> errors,String message) {
		super(message);
		this.errors = errors;
	}

	
	public List<ValidationError> getErrors() {
		return errors;
	}
	
//	public boolean hasErrors() {
//		return errors != null && errors.size() > 0;
//	}


}
