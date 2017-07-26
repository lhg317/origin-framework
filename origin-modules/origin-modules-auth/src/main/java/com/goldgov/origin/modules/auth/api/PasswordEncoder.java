package com.goldgov.origin.modules.auth.api;

public interface PasswordEncoder {

	public String encode(String pwd);
}
