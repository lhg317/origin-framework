package com.goldgov.origin.core.web.token;

/**
 * 令牌验证失败时抛出此异常
 * @author LiuHG
 * @version 1.0
 */
public class TokenValidException extends RuntimeException{

	private static final long serialVersionUID = -3471642195230480793L;

	public TokenValidException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public TokenValidException(String arg0) {
		super(arg0);
	}

	public TokenValidException(Throwable arg0) {
		super(arg0);
	}

	
}
