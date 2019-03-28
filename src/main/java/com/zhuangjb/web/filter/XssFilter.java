package com.zhuangjb.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 防xss攻击过滤器
 * 
 * @author xuqc
 * @date 2011-5-17
 */
public class XssFilter implements Filter {
	private static final Log log = LogFactory.getLog(SqlInjectionFilter.class);

	// 用户自定义不过滤的URL,格式为正则表达式，如：/xxx.html,/xxx.do,/xxxxx/.*,.*/xxxxx/，其中.*代表任意字符
	private String ignoreUrls = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		if(filterConfig.getInitParameter("ignoreUrls") != null) {
			ignoreUrls = filterConfig.getInitParameter("ignoreUrls");
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String servletPath = httpRequest.getServletPath();

		// 跳过用户自定义忽略的页面
		if(ignoreUrls != null && ignoreUrls.length() > 0) {
			String[] arr = ignoreUrls.split(",");
			for(String s : arr) {
				if(servletPath.matches(s)) {
					log.debug("跳过用户自定义忽略的页面:" + servletPath);
					chain.doFilter(request, response);
					return;
				}
			}
		}


	}

	public void destroy() {
	}

}
