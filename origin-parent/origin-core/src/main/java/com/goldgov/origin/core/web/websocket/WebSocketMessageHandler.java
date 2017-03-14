package com.goldgov.origin.core.web.websocket;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
/**
 * WebSocket文本消息处理器，处理连接前、后和消息到达时。
 * @author LiuHG
 * @version 1.0
 */
public class WebSocketMessageHandler extends TextWebSocketHandler {

	private final List<WebSocketMessageSession> sessions = new ArrayList<WebSocketMessageSession>();
	
	private WebSocketMessage handler;
	
	public WebSocketMessageHandler(WebSocketMessage handler){
		this.handler = handler;
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		WebSocketMessageSession webSocketMessageSession = new WebSocketMessageSession(session);
		sessions.add(webSocketMessageSession);
		webSocketMessageSession.setSessions(Collections.unmodifiableCollection(sessions));
		handler.onConnection(webSocketMessageSession);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		WebSocketMessageSession webSocketMessageSession = new WebSocketMessageSession(session);
		webSocketMessageSession.setSessions(Collections.unmodifiableCollection(sessions));
		try {
			handler.onMessage(webSocketMessageSession,new String(message.asBytes(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("不支持编码格式：UTF-8", e);
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		WebSocketMessageSession webSocketMessageSession = new WebSocketMessageSession(session);
		webSocketMessageSession.setSessions(Collections.unmodifiableCollection(sessions));
		for (WebSocketMessageSession user : sessions) {
			if(user.getId().equals(webSocketMessageSession.getId())){
				sessions.remove(user);
				break;
			}
		}
		handler.onDisconnection(webSocketMessageSession);
	}
	
	
}
