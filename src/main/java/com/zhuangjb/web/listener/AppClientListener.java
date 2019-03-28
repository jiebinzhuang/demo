package com.zhuangjb.web.listener;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhuangjb.util.WebUtils;

public class AppClientListener implements ServletContextListener, HttpSessionListener {
	private static final Log log = LogFactory.getLog(AppClientListener.class);
	private static final Map<String, Map<String, String>> initParameters = new HashMap<String, Map<String, String>>();

	public static Map<String, Map<String, String>> getInitParameters() {
		return initParameters;
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		long startTime = System.currentTimeMillis();

		// 获取服务器信息
		if (WebUtils.getServerInfo() == null) {
			WebUtils.setServerInfo(sce.getServletContext().getServerInfo());
		}

		// 获取当前应用的上下文(非安全方式，因为默认并没有暴露相关接口，所以采用类反射强行获取)
		String ctxPath = WebUtils.getContextPath(sce);
		WebUtils.setServerInfo(WebUtils.getServerInfo());
		// System.setProperty("webapp.root", ctxPath);

		Map<String, String> map = new HashMap<String, String>();
		Enumeration<?> e = sce.getServletContext().getInitParameterNames();
		while (e.hasMoreElements()) {
			String key = e.nextElement().toString();
			map.put(key, sce.getServletContext().getInitParameter(key));
		}
		initParameters.put(ctxPath, map);

		long endTime1 = System.currentTimeMillis();
		log.debug("执行耗时:" + (endTime1 - startTime) + "毫秒.");
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// 释放动作,如果需要
		log.debug("contextDestroyed:" + sce.getServletContext().getServerInfo());
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		log.debug("sessionCreated:" + se.getSession().getId());
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		log.debug("sessionDestroyed:" + se.getSession().getId());
	}

}
