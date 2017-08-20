package com.goldgov.origin.modules.organization.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface OrgObjectHandler {

	String doHandle(String orgID,HttpServletRequest request,Model model) throws Exception; 
}
