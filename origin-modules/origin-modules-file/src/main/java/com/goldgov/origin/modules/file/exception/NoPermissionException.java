package com.goldgov.origin.modules.file.exception;

public class NoPermissionException extends Exception{

	private static final long serialVersionUID = -4399996069290611822L;

	public NoPermissionException() {
		super();
	}

	public NoPermissionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NoPermissionException(String arg0) {
		super(arg0);
	}

	public NoPermissionException(Throwable arg0) {
		super(arg0);
	}
	
}
