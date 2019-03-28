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

/**
 * GZIP压缩过滤器<br>
 * 2011-4-8
 * 
 * @author fangw
 */
public class GZIPFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		if(this.isGZIPSupported((HttpServletRequest) request)) {
			GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper((HttpServletResponse) response);

			chain.doFilter(request, wrappedResponse);
			wrappedResponse.finishResponse();

			return;
		}

		chain.doFilter(request, response);
	}

	public void destroy() {

	}

	/**
	 * Convenience method to test for GZIP cababilities
	 * 
	 * @param req
	 *            The current user request
	 * @return boolean indicating GZIP support
	 */
	private boolean isGZIPSupported(HttpServletRequest req) {
		String browserEncodings = req.getHeader("accept-encoding");
		boolean supported = ((browserEncodings != null) && (browserEncodings.indexOf("gzip") != -1));

		String userAgent = req.getHeader("user-agent");

		if((userAgent != null) && userAgent.startsWith("httpunit")) {
			return false;
		} else {
			return supported;
		}
	}
}
