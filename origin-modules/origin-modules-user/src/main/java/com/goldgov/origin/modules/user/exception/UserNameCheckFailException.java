package com.goldgov.origin.modules.user.exception;

public class UserNameCheckFailException extends Exception{

	private static final long serialVersionUID = 9002442103432162440L;

	public UserNameCheckFailException() {
		super();
	}

	public UserNameCheckFailException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNameCheckFailException(String message) {
		super(message);
	}

	public UserNameCheckFailException(Throwable cause) {
		super(cause);
	}

	
}
