package com.zhuangjb.web.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.mvc.View;

/**
 * 将数据采用json方式输出的试图实现
 * 
 * @author zozoh(zozohtnt@gmail.com)
 * @author mawn(ming300@gmail.com)
 */
public class UTF8JsonView implements View {
	private final JsonFormat format;

	private Object data;

	public void setData(Object data) {
		this.data = data;
	}

	public UTF8JsonView(JsonFormat format) {
		this.format = format;
	}

	public void render(HttpServletRequest req, HttpServletResponse resp, Object obj) throws IOException {
		resp.setHeader("Cache-Control", "no-cache");
		resp.setContentType("text/html");
		write(resp, null == obj ? data : obj, format);
	}

	/**
	 * 它将对象序列化成 JSON 字符串，并写入 HTTP 响应
	 * 
	 * @param resp
	 *            响应对象
	 * @param obj
	 *            数据对象
	 * @param format
	 *            JSON 的格式化方式
	 * @throws IOException
	 *             写入失败
	 */
	public void write(HttpServletResponse resp, Object obj, JsonFormat format) throws IOException {
		resp.setHeader("Cache-Control", "no-cache");
		// resp.setContentType("text/plain");//extjs调用时，无法处理
		resp.setContentType("text/html");
		// resp.setContentType("text/json");//会变成下载

		// by mawm 改为直接采用resp.getWriter()的方式直接输出!
		if (obj != null && obj instanceof Throwable) {
			// 异常处理
			Throwable t = (Throwable) obj;

			Map<String, String> map = new HashMap<String, String>();
			map.put("statusCode", "500");
			map.put("message", t.getMessage());
			map.put("navTabId", "");
			map.put("callbackType", "");
			map.put("forwardUrl", "");

			Json.toJson(resp.getWriter(), map, format);
		} else {
			Json.toJson(resp.getWriter(), obj, format);
		}

		resp.flushBuffer();
	}

}
