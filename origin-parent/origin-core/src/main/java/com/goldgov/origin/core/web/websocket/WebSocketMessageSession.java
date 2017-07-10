package com.goldgov.origin.core.web.websocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.Principal;
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
	
	public Principal getPrincipal(){
		return session.getPrincipal();
	}

	/**
	 * 给当前会话发送消息
	 * @param message 消息内容
	 */
	public void sendMessage(String message) {
		try {
			session.sendMessage(new TextMessage(message));
		} catch (IOException e) {
			throw new MessageException("消息发送异常",e);
		}
	}
	
	/**
	 * 给指定的会话发送消息
	 * @param message 消息内容
	 * @param session 会话对象
	 */
	public void sendMessage(String message,WebSocketMessageSession session) {
		WebSocketSession wsSession = session.unwrap();
		try {
			if(wsSession.isOpen()){
				wsSession.sendMessage(new TextMessage(message));
			}
		} catch (Exception e) {
			throw new MessageException("消息发送异常",e);
		}
	}
	
	/**
	 * 广播消息，给所有当前会话群发消息
	 * @param message
	 */
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
	
	/**
	 * 根据会话ID得到对应的Session对象
	 * @param id
	 * @return
	 */
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
	
	/**
	 * 返回所有会话对象
	 * @return
	 */
	public Collection<WebSocketMessageSession> getSessions(){
		return sessions;
	}
	
	/**
	 * 获取当前会话总数
	 * @return 当前会话总数
	 */
	public int getCount(){
		return sessions.size();
	}
	
	/**
	 * 返回原生WebSocket的Session对象
	 * @return
	 */
	private WebSocketSession unwrap(){
		return session;
	}

}
