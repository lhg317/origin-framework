package com.goldgov.origin.core.service;


/**
 * 
 * @author LiuHG
 * @version 1.0
 */
public class SortField {

	private String name;
	private SortDirection direction;
	
	public SortField(){
		this.direction = SortDirection.ASC;
	}
	
	public SortField(String name){
		this.name = name;
		this.direction = SortDirection.DESC;
	}
	
	public SortField(String name,SortDirection direction){
		this.name = name;
		this.direction = direction;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SortDirection getDirection() {
		return direction;
	}

	public void setDirection(SortDirection direction) {
		this.direction = direction;
	}

	/**
	 * 排序顺序
	 * @author LiuHG
	 * @version 1.0
	 */
	public enum SortDirection{
		ASC,DESC;
	}

}
