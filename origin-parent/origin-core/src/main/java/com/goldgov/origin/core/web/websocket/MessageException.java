package com.goldgov.origin.core.web.websocket;

public class MessageException extends RuntimeException{

	private static final long serialVersionUID = 1488364763632914875L;

	public MessageException() {
		super();
	}

	public MessageException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageException(String message) {
		super(message);
	}

	public MessageException(Throwable cause) {
		super(cause);
	}

}
