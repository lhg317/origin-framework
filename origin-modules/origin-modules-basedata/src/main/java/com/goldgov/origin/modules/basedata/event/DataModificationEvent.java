package com.goldgov.origin.modules.basedata.event;

import org.springframework.context.ApplicationEvent;

/**
 * 基础数据修改后触发的 事件
 * @author LiuHG
 * @version 1.0
 */
public class DataModificationEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1112585872100805099L;
	private final Object data;
	
	public DataModificationEvent(Object data,EventType eventType) {
		super(data);
		this.data = data;
	}

	public enum EventType{
		ADD,UPDATE,DELETE;
	}

	public Object getData() {
		return data;
	}
	
}
