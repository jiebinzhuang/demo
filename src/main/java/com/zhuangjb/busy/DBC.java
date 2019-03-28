package com.zhuangjb.busy;

/**
 * 数据库常量
 */
public interface DBC {
	/**
	 * 默认页大小
	 */
	public final static int DEFAULT_PAGE_SIZE = 10;

	public final static String SECRET_KEY = "zhuangjb.name1234567890!@";

	/**
	 * 队列
	 */
	public final static String MQ_ORDER_API = "order_api";
	public final static String MQ_ORDER_PROCESS = "order_process";
	public final static String MQ_ORDER_REPORT = "order_report";

	/**
	 * 表
	 */
	public final static String USER_用户 = "user";
	public final static String SEQ = "seq";
	public final static String LOG = "log";
	public final static String GSD = "gsd";

	public final static String WEIXIN = "weixin";

	public final static String ts = "ts";
	public final static String dr = "dr";
	public final static String enable = "enable";
	public final static String username = "username";
	public final static String password = "password";
	public final static String id = "_id";
	public final static String op = "op";
	public final static String tel = "tel";
	public final static String updateTs = "updateTs";
	public final static String updateTime = "updateTime";
	public final static String createTs = "createTs";
	public final static String createTime = "createTime";
	public final static String commitTs = "commitTs";
	public final static String commitTime = "commitTime";
	public final static String reportTs = "reportTs";
	public final static String reportTime = "reportTime";
	public final static String notifyTs = "notifyTs";
	public final static String notifyTime = "notifyTime";

}
