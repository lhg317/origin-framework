package com.goldgov.origin.core.web.interceptor.handler.impl;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.goldgov.origin.core.web.interceptor.handler.IRequestHandler;
import com.goldgov.origin.core.web.token.ITokenGenerator;
import com.goldgov.origin.core.web.token.TokenValidException;
import com.goldgov.origin.core.web.token.UuidTokenGenerator;
import com.goldgov.origin.core.web.token.WebToken;
import com.goldgov.origin.core.web.token.WebToken.TokenHandleType;

/**
 * 重复提交检查
 * @author LiuHG
 * @version 1.0
 */
public class TokenCheckHandler implements IRequestHandler{

	private ITokenGenerator tokenGenerator;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod)handler;
//			Object bean = handlerMethod.getBean();
			
			Method method = handlerMethod.getMethod();
			WebToken token = method.getAnnotation(WebToken.class);
			
//			String reqType = getRequestType(request);
			
			if(tokenGenerator == null){
				tokenGenerator = new UuidTokenGenerator();
			}
			
			if(token != null){
				HttpSession session = request.getSession();
				if(token.handle().equals(TokenHandleType.GENERATE)){
					session.setAttribute(WebToken.DEFAULT_TOKEN_NAME, tokenGenerator.generate());
				}else if(token.handle().equals(TokenHandleType.VERIFY)){
					Object sessionToken = session.getAttribute(WebToken.DEFAULT_TOKEN_NAME);
					String requestToken = request.getParameter(WebToken.DEFAULT_TOKEN_NAME);
					if(sessionToken != null && requestToken != null && sessionToken.equals(requestToken)){
						session.removeAttribute(WebToken.DEFAULT_TOKEN_NAME);
						return true;
					}else{
//						request.getHeader("Referer")//http://127.0.0.1:8081/user/preAdd
						if(!token.forward().equals("")){
							try {
								request.getRequestDispatcher(token.forward()).forward(request, response);
							} catch (ServletException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return false;
						}
						throw new TokenValidException(request.getRequestURI());
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		return true;
	}


	public void setTokenGenerator(ITokenGenerator tokenGenerator){
		this.tokenGenerator = tokenGenerator;
	}

	@Override
	public void completion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
