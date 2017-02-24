package com.goldgov.origin.core.web.token;

import java.util.UUID;

public class UuidTokenGenerator implements ITokenGenerator{

	@Override
	public String generate() {
		return UUID.randomUUID().toString();
	}

}
