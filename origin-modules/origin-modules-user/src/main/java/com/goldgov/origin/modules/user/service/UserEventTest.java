package com.goldgov.origin.modules.user.service;

import org.springframework.stereotype.Component;

import com.goldgov.origin.modules.user.event.AddUserEvent;

@Component
public class UserEventTest implements AddUserEvent{

	@Override
	public void onBeforeEvent(User event) {
		System.out.println("添加前"+event);
		
	}

	@Override
	public void onAfterEvent(User event) {
		System.out.println("添加后"+event);
	}

}
