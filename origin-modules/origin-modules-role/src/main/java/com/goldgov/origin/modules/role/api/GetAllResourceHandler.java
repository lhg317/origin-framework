package com.goldgov.origin.modules.role.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface GetAllResourceHandler {

	String doHandle(HttpServletRequest request, Model model) throws Exception; 
}
