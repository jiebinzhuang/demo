package com.zhuangjb.web.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 防sql注入过滤器
 * 
 * @author fangw
 * @date 2011-5-17
 */
public class SqlInjectionFilter implements Filter {
	private static final Log log = LogFactory.getLog(SqlInjectionFilter.class);

	// 用户自定义不过滤的URL,格式为正则表达式，如：/xxx.html,/xxx.do,/xxxxx/.*,.*/xxxxx/，其中.*代表任意字符
	private String ignoreUrls = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		if (filterConfig.getInitParameter("ignoreUrls") != null) {
			ignoreUrls = filterConfig.getInitParameter("ignoreUrls");
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String servletPath = httpRequest.getServletPath();

		// 跳过用户自定义忽略的页面
		if (ignoreUrls != null && ignoreUrls.length() > 0) {
			String[] arr = ignoreUrls.split(",");
			for (String s : arr) {
				if (servletPath.matches(s)) {
					log.debug("跳过用户自定义忽略的页面:" + servletPath);
					chain.doFilter(request, response);
					return;
				}
			}
		}

		// 对于参照的请求，这里放行，需要的话在具体的处理类中进行检查
		if (servletPath.startsWith("/ref/")) {
			log.debug("跳过sql注入检查:" + servletPath);
			chain.doFilter(request, response);
			return;
		}

		@SuppressWarnings("rawtypes")
		Map map = httpRequest.getParameterMap();
		List<String> args = new ArrayList<String>();
		for (Object key : map.keySet()) {
			String[] arr = (String[]) map.get(key);
			for (String value : arr) {
				args.add(value);
			}
		}
		try {

			chain.doFilter(request, response);
		} catch (Exception e) {
			httpResponse.setStatus(500);
			// if (servletPath.endsWith("json")) {
			// Ajax请求
			String json = "{\"success\":false,\"msg\":\"" + e.getMessage() + "!\"}";
			this.writeResponse(httpRequest, httpResponse, json);
			return;
			// } else {
			// throw new RuntimeException(e);
			// }
		}
	}

	private void writeResponse(HttpServletRequest request, HttpServletResponse response, String json)
			throws IOException {
		response.setContentType("text/html");
		String encoding = request.getCharacterEncoding();
		if (encoding == null) {
			encoding = "UTF-8";
		}
		ServletOutputStream sos = response.getOutputStream();
		InputStream is = new ByteArrayInputStream(json.getBytes(encoding));
		byte[] buf = new byte[1024];
		int len;
		while ((len = is.read(buf)) != -1) {
			sos.write(buf, 0, len);
		}
		is.close();
		sos.flush();
		sos.close();
	}

	public void destroy() {
	}

}
