package com.goldgov.origin.modules.basedata.event;

import org.springframework.context.ApplicationEvent;

/**
 * 基础数据修改后触发的 事件
 * @author LiuHG
 * @version 1.0
 */
public class BaseDataEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1112585872100805099L;
	private final Object data;
	private final EventType eventType;
	
	public BaseDataEvent(Object data,EventType eventType) {
		super(data);
		this.data = data;
		this.eventType = eventType;
	}

	public enum EventType{
		ADD,UPDATE,DELETE,LIST;
	}

	public Object getData() {
		return data;
	}

	public EventType getEventType() {
		return eventType;
	}
}
