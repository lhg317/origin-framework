package com.goldgov.origin.core.web.annotation;

/**
 * 模块操作类型定义
 * @author LiuHG
 * @version 1.0
 */
public enum OperateType {

	/**
	 * 保存（新增）操作
	 */
	ADD,
	/**
	 * 删除操作
	 */
	DELETE,
	/**
	 * 更新操作
	 */
	UPDATE,
	/**
	 * 查看操作（只查询单条对象）
	 */
	FIND,
	/**
	 * 集合查询操作（查询多条对象）
	 */
	FIND_LIST,
	/**
	 * 其他操作，一般用于非增删改查的操作。
	 */
	NONE;
	
}
