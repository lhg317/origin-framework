package com.goldgov.origin.security.filter.remote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.goldgov.origin.security.authentication.remote.RemoteAuthenticationToken;

public class RemoteAuthenticationFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
			//FIXME
	        grantedAuths.add(new SimpleGrantedAuthority("ACTUATOR"));
			RemoteAuthenticationToken remoteAuthenticationToken = new RemoteAuthenticationToken(grantedAuths);
			if(!updateCredentials(request,remoteAuthenticationToken)){
				chain.doFilter(request, response);
				return;
			}
			SecurityContextHolder.getContext().setAuthentication(remoteAuthenticationToken);
		}else if(authentication instanceof RemoteAuthenticationToken){
			if(!updateCredentials(request,(RemoteAuthenticationToken)authentication)){
				chain.doFilter(request, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}
	
	private boolean updateCredentials(ServletRequest request,RemoteAuthenticationToken remoteAuthenticationToken){
		String expires = request.getParameter("expires");
		String code = request.getParameter("code");
		
		if(expires == null || code == null){			
			return false;
		}
		long expiresMill = -1;
		try{
			expiresMill = Long.parseLong(expires);
		}catch(NumberFormatException e){
			return false;
		}
		remoteAuthenticationToken.setPrincipal(code);
		remoteAuthenticationToken.setExpires(expiresMill);
		return true;
	}

}
