package com.zhuangjb.busy;

import org.bson.Document;

import com.zhuangjb.mongodb.MongoDAO;
import com.zhuangjb.system.Constants;
import com.zhuangjb.util.DateUtils;
import com.zhuangjb.util.WebUtils;
import com.zhuangjb.web.filter.ContextHolder;

public class LogHelper {
	// private static final Log log = LogFactory.getLog(LogHelper.class);

	public static String getUserName() {
		if (ContextHolder.getRequest() != null) {
			if (ContextHolder.getRequest().getSession(false) != null) {
				if (ContextHolder.getRequest().getSession().getAttribute(Constants.USERNAME) != null) {
					return ContextHolder.getRequest().getSession().getAttribute(Constants.USERNAME).toString();
				}
			}
		}
		return null;
	}

	public static void log(String tag, String content, boolean isSuccess) {
		log(tag, content, null, isSuccess);
	}

	public static void log(String tag, String content, Object data1, boolean isSuccess) {
		Document doc = new Document();
		doc.append("tag", tag);
		doc.append("content", content);
		doc.append("isSuccess", isSuccess);
		doc.append("time", DateUtils.getNowTime().getTime());
		doc.append("date", DateUtils.getCurrentDate());
		doc.append("ts", DateUtils.getTS());

		if (getUserName() != null) {
			doc.append("username", getUserName());
		}

		if (ContextHolder.getRequest() != null) {
			String ip = WebUtils.getIpAddr(ContextHolder.getRequest());
			doc.append("ip", ip);
		}

		if (data1 != null) {
			doc.put("data1", data1);
		}
		MongoDAO.getInstance().insert(DBC.LOG, doc);
	}

	public static void log(String tag, String content, Object data1, Object data2, boolean isSuccess) {
		Document doc = new Document();
		doc.append("tag", tag);
		doc.append("content", content);
		doc.append("isSuccess", isSuccess);
		doc.append("time", DateUtils.getNowTime().getTime());
		doc.append("date", DateUtils.getCurrentDate());
		doc.append("ts", DateUtils.getTS());

		if (getUserName() != null) {
			doc.append("username", getUserName());
		}

		if (ContextHolder.getRequest() != null) {
			String ip = WebUtils.getIpAddr(ContextHolder.getRequest());
			doc.append("ip", ip);
		}

		if (data1 != null) {
			doc.put("data1", data1);
		}
		if (data2 != null) {
			doc.put("data2", data2);
		}
		MongoDAO.getInstance().insert(DBC.LOG, doc);
	}
}
