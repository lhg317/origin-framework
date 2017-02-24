package com.goldgov.origin.core.web.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goldgov.origin.core.web.annotation.OperatingType;

public interface CustomConstraintValidator {

	public boolean isValid(OperatingType type,HttpServletRequest request,HttpServletResponse response) throws ValidationException;
}
