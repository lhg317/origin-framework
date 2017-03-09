package com.goldgov.origin.core;

/**
 * 框架使用到的用于key名称的统一定义类。
 * @author LiuHG
 * @version 1.0
 */
public class Keys {

	public static final String CURRENT_TOKEN_NAME = "$CURRENT_TOKEN_NAME$";
//	public static final String CURRENT_SORT_FIELD_NAME = "$CURRENT_SORT_FIELD_NAME$";
//	public static final String CURRENT_SORT_DIRECTION_NAME = "$CURRENT_SORT_DIRECTION_NAME$";
	
	public static final String ORIGIN_EXCEPTION = "$PUZZLE_EXCEPTION$";
	public static final String ORIGIN_MODEL_PATH = "$PUZZLE_MODEL_PATH$";
	public static final String DEFAULT_LOCALE = "$DEFAULT_LOCALE$";
	public static final String VALIDATION_ERROR = "$VALIDATION_ERROR$";
	
	public static final String CURRENT_USER = "PUZZLE_CURRENT_USER";
	
	public static final String REMOTE_INFO_HEADER_NAME = "Puzzle-Remote-Info";
	
	public static final String REMOTE_CALL_HEADER_NAME = "Puzzle-Remote-Call";
	public static final String REMOTE_CALL_CONTEXT_NAME = "Puzzle-Remote-ContextName";
	public static final String REMOTE_CALL_FROM = "Puzzle-Remote-From";
	public static final String REMOTE_CALL_USER = "Puzzle-Remote-User";
	
	public static final String REMOTE_CALL_PARAM_SEPARATOR = "$";
	
	/*
	 * 缓存Key
	 */
	public static final String CACHE_CODE_CONFIG_DEPOSITORY = "CACHE_CODE_CONFIG_DEPOSITORY";
	public static final String CACHE_CODE_PROTECTED_RESOURCE = "CACHE_CODE_PROTECTED_RESOURCE";
	public static final String CACHE_CODE_PROTECTED_RESOURCE_MAPPING = "CACHE_CODE_PROTECTED_RESOURCE_MAPPING";
	
}
