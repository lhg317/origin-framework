package com.goldgov.origin.webgate.sample;


import java.security.Principal;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.goldgov.origin.core.web.websocket.WebSocketMessage;
import com.goldgov.origin.core.web.websocket.WebSocketMessageSession;
import com.goldgov.origin.security.UserToken;

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
		Principal principal = session.getPrincipal();
		session.sendBroadcast("欢迎"+(getUserToken(principal)).getUserName()+"("+session.getRemoteAddress().getHostName()+")的加入，当前用户数：" + session.getCount());
	}

	@Override
	public void onMessage(WebSocketMessageSession session, String message) {
		Principal principal = session.getPrincipal();
		session.sendBroadcast((getUserToken(principal)).getUserName() + "&nbsp;say：" + message);
	}

	@Override
	public void onDisconnection(WebSocketMessageSession session)
			throws Exception {
		Principal principal = session.getPrincipal();
		session.sendBroadcast(getUserToken(principal).getUserName()+"("+session.getRemoteAddress().getHostName()+")退出了，当前用户数：" + session.getCount());
	}

	@Override
	public String getSessionID(ServerHttpRequest request) {
		return null;
	}
	
	public UserToken getUserToken(Principal principal){
		if(principal instanceof Authentication){
			return (UserToken)((Authentication) principal).getPrincipal();
		}
		return null;
	}

}

