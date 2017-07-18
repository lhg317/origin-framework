package com.goldgov.origin.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.goldgov.origin.security.authentication.RemoteAuthenticationToken;

public class RemoteAuthenticationFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			String user = request.getParameter("u");
			String code = request.getParameter("code");
			
			if(user == null){
				chain.doFilter(request, response);
				return;
			}

			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
	        grantedAuths.add(new SimpleGrantedAuthority("ACTUATOR"));
	        
			RemoteAuthenticationToken remoteAuthenticationToken = new RemoteAuthenticationToken("user",grantedAuths);
			SecurityContextHolder.getContext().setAuthentication(remoteAuthenticationToken);
		}
		chain.doFilter(request, response);
	}

}
