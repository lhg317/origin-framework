package com.goldgov.origin.core.web.websocket;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor{

	private WebSocketMessage handler;

	public HandshakeInterceptor(WebSocketMessage handler){
		this.handler = handler;
	}
	
	@Override
    public boolean beforeHandshake(ServerHttpRequest request,  
            ServerHttpResponse response, WebSocketHandler wsHandler,  
            Map<String, Object> attributes) throws Exception {  
		handler.beforeHandshake(request, response);
        return super.beforeHandshake(request, response, wsHandler, attributes);  
    }
  
    @Override  
    public void afterHandshake(ServerHttpRequest request,  
            ServerHttpResponse response, WebSocketHandler wsHandler,  
            Exception ex) {  
    	handler.afterHandshake(request, response);
        super.afterHandshake(request, response, wsHandler, ex);  
    }  
}
