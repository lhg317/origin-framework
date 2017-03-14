package com.goldgov.origin.core.web.websocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * WebSocket消息会话对象
 * @author LiuHG
 * @version 1.0
 */
public class WebSocketMessageSession {

//	private String name;
	
	private WebSocketSession session;

	private Collection<WebSocketMessageSession> sessions;
	
	public WebSocketMessageSession(WebSocketSession session){
		this.session = session;
	}
	
	public String getId() {
		return session.getId();
	}

	public Map<String, Object> getAttributes() {
		return session.getAttributes();
	}
	
	public Object getAttribute(String name) {
		return getAttributes().get(name);
	}

	public InetSocketAddress getRemoteAddress() {
		return session.getRemoteAddress();
	}

	/**
	 * 给当前人发送回复消息
	 * @param message 消息内容
	 */
	public void sendMessage(String message) {
		try {
			session.sendMessage(new TextMessage(message));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message,WebSocketMessageSession s) {
		WebSocketSession wsSession = s.unwrap();
		try {
			if(wsSession.isOpen()){
				wsSession.sendMessage(new TextMessage(message));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendBroadcast(String message){
		for (WebSocketMessageSession s : sessions) {
			sendMessage(message,s);
		}
	}

	public boolean isOpen() {
		return session.isOpen();
	}

	public void close() throws IOException {
		session.close();
	}
	
	public WebSocketMessageSession getSession(String id){
		for (WebSocketMessageSession s : sessions) {
			if(s.getId().equals(id)){
				return s;
			}
		}
		return null;
	}
	
	void setSessions(Collection<WebSocketMessageSession> collection){
		this.sessions = collection;
	}
	
	public Collection<WebSocketMessageSession> getSessions(){
		return sessions;
	}
	
	public int getCount(){
		return sessions.size();
	}
	
	private WebSocketSession unwrap(){
		return session;
	}

}
