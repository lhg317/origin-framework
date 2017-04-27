package com.goldgov.origin.modules.file.exception;

public class UploadLimitException extends RuntimeException{

	private static final long serialVersionUID = 2714871874613421893L;

	public UploadLimitException() {
		super();
	}

	public UploadLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	public UploadLimitException(String message) {
		super(message);
	}

	public UploadLimitException(Throwable cause) {
		super(cause);
	}

	
}
