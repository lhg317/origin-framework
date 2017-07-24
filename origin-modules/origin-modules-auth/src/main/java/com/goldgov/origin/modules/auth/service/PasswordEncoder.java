package com.goldgov.origin.modules.auth.service;

public interface PasswordEncoder {

	public String encode(String pwd);
}
