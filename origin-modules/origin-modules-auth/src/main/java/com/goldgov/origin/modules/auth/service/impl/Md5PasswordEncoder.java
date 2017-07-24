package com.goldgov.origin.modules.auth.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.goldgov.origin.modules.auth.service.PasswordEncoder;

public class Md5PasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(String pwd) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("请求特定的加密算法而它在该环境中不可用",e);
		}
		md.update(pwd.getBytes());
		return new BigInteger(1, md.digest()).toString(16);
	}

}
