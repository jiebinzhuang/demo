package com.zhuangjb.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.json.JsonFormat;
import org.nutz.mvc.Mvcs;

import com.zhuangjb.system.Constants;

/**
 * 登录校验过滤器
 * 
 * @author fangw
 */
public class LoginFilter implements Filter {
	// private static final Log logger =
	// LogFactory.getLog(LoginClientFilter.class);

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if ("/loginCheck.action".equals(request.getServletPath())) {
			chain.doFilter(req, res);
			return;
		}

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute(Constants.USERNAME) == null) {
			// 未登录
			if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("statusCode", "301");
				map.put("message", "登陆超时");
				// map.put("navTabId", "");
				// map.put("callbackType", "");
				// map.put("forwardUrl", "");

				response.setHeader("Cache-Control", "no-cache");
				response.setContentType("text/html");
				Mvcs.write(response, map, JsonFormat.full());
			} else {
				response.sendRedirect("/index.action");
			}
			return;
		} else {
			// 已登录
			PrincipalImpl principal = new PrincipalImpl(session.getAttribute(Constants.USERNAME).toString());
			chain.doFilter(new LoginRequestWrapper((HttpServletRequest) req, principal), res);
		}
	}

	@Override
	public void destroy() {
	}

}
