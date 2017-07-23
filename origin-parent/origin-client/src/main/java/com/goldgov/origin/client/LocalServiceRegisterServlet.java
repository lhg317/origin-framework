package com.goldgov.origin.client;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

//NOT USE
public class LocalServiceRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 3806096163826062346L;
	private final LocalServiceRegister localServiceRegister;
	
	public LocalServiceRegisterServlet(LocalServiceRegister localServiceRegister){
		this.localServiceRegister = localServiceRegister;
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		localServiceRegister.register();
	}

}
