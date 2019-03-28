package com.zhuangjb.system;

public class Constants {
	// public static final String JNDI = "java:comp/env/jdbc/ROEEE";
	public static final String USERNAME = "USERNAME";
	public static final String USERID = "USERID";

	/******** 角色 *****/
	public static final String ROLECODE = "ROLECODE";
	public static final String ROLECODE_SUPER_ADMIN = "super_admins";// 超级管理员
	public static final String ROLECODE_ADMIN = "admins";// 管理员
	public static final String ROLECODE_USER = "users";// 用户
	// public static final String ROLECODE_AGENT = "agent";//代理商

	public static final String DEFAULT_PAGE_NUM = "1";
	public static final String DEFAULT_NUM_PER_PAGE = "10";
	/** 上传文件-单个文件大小限制 */
	public static final long UPLOAD_FILE_MAX_SIZE = 1024 * 1024 * 100;
	/** 上传文件-总文件大小限制 */
	public static final long UPLOAD_MAX_SIZE = 1024 * 1024 * 100;
	/** 上传文件-设置普通字段名称和文件字段的文件名所采用的字符集编码 */
	public static final String UPLOAD_ENCODING = "UTF-8";
}
