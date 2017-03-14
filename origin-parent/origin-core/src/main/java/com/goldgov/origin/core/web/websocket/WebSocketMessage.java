package com.goldgov.origin.core.web.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

/**
 * WebSocket消息对象接口，由业务功能自行实现，并需要成为一恶搞SpringBean，
 * WebSocket消息推送及各种消息事件处理逻辑都需要在该接口实现中进行定义。
 * @author LiuHG
 * @version 1.0
 */
public interface WebSocketMessage {

	/**
	 * 请求映射路径，类似SpringMVC的RequestMappig的作用
	 * @return 请求映射的路径地址。
	 */
	public String webSocketMapping();
	
	/**
	 * 得到当前会话ID，如返回null，则使用HttpSession的id属性值。
	 * @param request ServerHttpRequest
	 * @return 会话ID
	 */
	public String getSessionID(ServerHttpRequest request);
	
	/**
	 * 与服务端握手前事件处理
	 * @param request ServerHttpRequest
	 * @param response ServerHttpResponse
	 */
	public void beforeHandshake(ServerHttpRequest request,
            ServerHttpResponse response);
	/**
	 * 与服务端握手后事件处理
	 * @param request ServerHttpRequest
	 * @param response ServerHttpResponse
	 */
	public void afterHandshake(ServerHttpRequest request,
            ServerHttpResponse response);
	
	/**
	 * 连接成功时事件处理
	 * @param session
	 * @throws Exception
	 */
	public void onConnection(WebSocketMessageSession session)throws Exception;
	
	/**
	 * 推送消息给客户端
	 * @param session WebSocket消息会话对象
	 * @param message 发送的消息内容
	 */
	public void onMessage(WebSocketMessageSession session,String message);
	
	/**
	 * 连接关闭时事件处理
	 * @param session
	 * @throws Exception
	 */
	public void onDisconnection(WebSocketMessageSession session)throws Exception;
	
}
