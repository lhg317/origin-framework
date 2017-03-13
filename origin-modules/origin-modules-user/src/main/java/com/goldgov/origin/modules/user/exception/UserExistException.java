package com.goldgov.origin.modules.user.exception;

public class UserExistException extends Exception{

	private static final long serialVersionUID = -7793224810437430630L;

	public UserExistException() {
		super();
	}

	public UserExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserExistException(String message) {
		super(message);
	}

	public UserExistException(Throwable cause) {
		super(cause);
	}

	
}
