package com.zhuangjb.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 编码过滤器<br>
 * 2011-4-8
 * 
 * @author fangw
 */
public class CharacterEncodingFilter implements Filter {
	private String encoding = "utf-8";
	private boolean forceEncoding = false;

	public String getEncoding() {
		return encoding;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		if(filterConfig.getInitParameter("encoding") != null) {
			encoding = filterConfig.getInitParameter("encoding");
		}
		if(filterConfig.getInitParameter("forceEncoding") != null) {
			forceEncoding = Boolean.parseBoolean(filterConfig.getInitParameter("forceEncoding"));
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		if(encoding != null && (forceEncoding || request.getCharacterEncoding() == null)) {
			request.setCharacterEncoding(encoding);
			if(forceEncoding) {
				response.setCharacterEncoding(encoding);
			}
		}
		chain.doFilter(new CharacterEncodingRequestWrapper((HttpServletRequest) request), response);
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

}
