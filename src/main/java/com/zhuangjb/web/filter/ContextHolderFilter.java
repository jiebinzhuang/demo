package com.zhuangjb.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContextHolderFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		ContextHolder.setRequest(httpRequest);
		ContextHolder.setResponse(httpResponse);
		try {
			chain.doFilter(request, response);
		} finally {
			ContextHolder.remove();
		}
	}

	public void destroy() {

	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
