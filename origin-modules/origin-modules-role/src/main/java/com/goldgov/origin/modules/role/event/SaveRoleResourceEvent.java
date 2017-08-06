package com.goldgov.origin.modules.role.event;

import org.springframework.context.ApplicationEvent;

/**
 * 当保存角色资源时触发的事件
 * @author LiuHG
 * @version 1.0
 */
public class SaveRoleResourceEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1112585872100805099L;

	private final String roleID;
	private final String[] resourceOperates;
	
	public SaveRoleResourceEvent(String roleID,String[] resourceOperates) {
		super(roleID);
		this.roleID = roleID;
		this.resourceOperates = resourceOperates;
	}

	public String getRoleID() {
		return roleID;
	}

	public String[] getResourceOperates() {
		return resourceOperates;
	}

}
