package com.goldgov.origin.webgate;


import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.goldgov.origin.core.web.websocket.WebSocketMessage;
import com.goldgov.origin.core.web.websocket.WebSocketMessageSession;

@Component
public class TestWebSocketMessageHandler implements WebSocketMessage{

	@Override
	public String webSocketMapping() {
		return "/myHandler";
	}

	@Override
	public void beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnection(WebSocketMessageSession session) throws Exception {
		System.out.println("success!!!!");
		session.sendBroadcast("欢迎"+session.getRemoteAddress().getHostName()+"的加入，当前用户数：" + session.getCount());
	}

	@Override
	public void onMessage(WebSocketMessageSession session, String message) {
		System.out.println(message);
		session.sendBroadcast(session.getRemoteAddress().getHostName() + "&nbsp;say：" + message);
	}

	@Override
	public void onDisconnection(WebSocketMessageSession session)
			throws Exception {
		session.sendBroadcast(session.getRemoteAddress().getHostName()+"退出了，当前用户数：" + session.getCount());
	}

	@Override
	public String getSessionID(ServerHttpRequest request) {
		return null;
	}

}

