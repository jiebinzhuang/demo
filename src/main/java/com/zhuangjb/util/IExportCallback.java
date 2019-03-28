package com.zhuangjb.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 导出回调
 * 
 * @author fangw
 */
public interface IExportCallback {
	HttpServletRequest getRequest();

	HttpServletResponse getResponse();

	/**
	 * 设置上下文
	 */
	List<Map<String, Object>> getContext();

	/**
	 * 导出的文件名称(浏览器下载的默认名称)
	 */
	String getFileName();

	/**
	 * 导出类型:excel,txt,pdf
	 */
	String getType();

	/**
	 * { "fullname", "String","姓名" }, { "xxx", "Number", "数值" }
	 */
	List<String[]> getFieldMetadata();
}
