package com.goldgov.origin.core.web.websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration("WebSocketConfig")
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private List<WebSocketMessage> handlers = new ArrayList<WebSocketMessage>();
    
	@Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		for (int i = 0; i < handlers.size(); i++) {
			HandshakeInterceptor handshakeInterceptor = new HandshakeInterceptor(handlers.get(i));
			WebSocketMessageHandler webSocketMessageHandler = new WebSocketMessageHandler(handlers.get(i));
			registry.addHandler(webSocketMessageHandler, handlers.get(i).webSocketMapping())
			.addInterceptors(handshakeInterceptor);
			registry.addHandler(webSocketMessageHandler, handlers.get(i).webSocketMapping() + "/sockjs")
			.addInterceptors(handshakeInterceptor).setAllowedOrigins("*").withSockJS();
		}
    }

	public void addHandler(WebSocketMessage handler){
		handlers.add(handler);
	}
}
