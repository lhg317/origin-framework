package com.goldgov.origin.core.web.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goldgov.origin.core.web.annotation.OperateType;

public interface CustomConstraintValidator {

	public boolean isValid(OperateType type,HttpServletRequest request,HttpServletResponse response) throws ValidationException;
}
