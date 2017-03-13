package com.goldgov.origin.core.service;

public interface ServiceEvent<T> {

	public void onBeforeEvent(T event);
	
	public void onAfterEvent(T event);
}
