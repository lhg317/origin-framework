package com.goldgov.origin.core.web.annotation;

/**
 * 模块操作类型定义
 * @author LiuHG
 * @version 1.0
 */
public enum OperatingType {

	/**
	 * 保存（新增）操作
	 */
	Save,
	/**
	 * 删除操作
	 */
	Delete,
	/**
	 * 更新操作
	 */
	Update,
	/**
	 * 查看操作（只查询单条对象）
	 */
	Find,
	/**
	 * 集合查询操作（查询多条对象）
	 */
	FindList,
	/**
	 * 其他操作，一般用于非增删改查的操作。
	 */
	None;
	
}
