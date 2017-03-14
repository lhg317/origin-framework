package com.goldgov.origin.core.web.websocket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * WebSocket消息处理器加载类，负责在应用启动过程中加载消息处理器
 * @author LiuHG
 * @version 1.0
 */
@Component
public class WebSocketMessageHandlerLoader implements BeanPostProcessor{

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired(required=false)
	private WebSocketConfig webSocketRegistry;
	
	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		if(arg0 instanceof WebSocketMessage){
			WebSocketMessage handler = (WebSocketMessage)arg0;
			if(webSocketRegistry == null){
				logger.warn("您当前的运行环境无法支持WebSocket，必须在Servlet3及JDK7+的环境中，映射地址没有生效：" + handler.webSocketMapping());
				return arg0;
			}
			webSocketRegistry.addHandler(handler);
		}
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		return arg0;
	}

}
