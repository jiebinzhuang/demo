package com.zhuangjb.web.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.net.URL;

import javax.management.MBeanServer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.security.HashUserRealm;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.management.MBeanContainer;
import org.mortbay.thread.QueuedThreadPool;

public class WebServer implements Runnable {
	private static final Log logger = LogFactory.getLog(WebServer.class);
	private MBeanServer mbeanServer = null;
	private Server httpServer = null;

	public MBeanServer getMbeanServer() {
		return mbeanServer;
	}

	public Server getHttpServer() {
		return httpServer;
	}

	// private void createDatabase() {
	// try {
	// Properties properties = SystemUtils.getDatabaseProperties();
	// DataSource ds = BasicDataSourceFactory.createDataSource(properties);
	// System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
	// "org.apache.naming.java.javaURLContextFactory");
	// System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
	// InitialContext ctx = new InitialContext();
	// ctx.createSubcontext("java:comp");
	// ctx.createSubcontext("java:comp/env");
	// ctx.createSubcontext("java:comp/env/jdbc");
	// // ctx.bind("java:comp/env/jdbc/ROEEE", ds);
	// ctx.bind(Constants.JNDI, ds);
	// } catch (Exception e) {
	// e.printStackTrace();
	// throw new RuntimeException(e);
	// }
	// // dao = new Dao("jdbc/ROEEE");
	// }

	@Override
	public void run() {
		logger.info("HttpServer启动...");
		// createDatabase();

		WebServerConfigVO adminConfigVO = WebServerConfigUtils.getWebServerConfig();
		httpServer = new Server();
		// 设置线程池
		QueuedThreadPool threadPool = new QueuedThreadPool();
		// threadPool.setMinThreads(10);
		threadPool.setMaxThreads(32);
		httpServer.setThreadPool(threadPool);
		// 设置连接参数
		Connector connector = new SelectChannelConnector();
		if (adminConfigVO.getHostname() != null && adminConfigVO.getHostname().length() > 0) {
			connector.setHost(adminConfigVO.getHostname());
		}
		// 设置监听端口
		connector.setPort(adminConfigVO.getPort());
		httpServer.setConnectors(new Connector[] { connector });
		URL url = WebServer.class.getResource("/webapp");
		String path = url.getPath();
		try {
			path = java.net.URLDecoder.decode(url.getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		WebAppContext context = new WebAppContext(path, "/");
		// 让web文件可以动态修改
		context.setDefaultsDescriptor(path + "/WEB-INF/webdefault.xml");
		httpServer.addHandler(context);
		httpServer.setStopAtShutdown(true);
		httpServer.setSendServerVersion(true);

		// 认证配置
		HashUserRealm realm = new HashUserRealm();
		realm.setName("admins");
		try {

			realm.setConfig(path + "/../realm.properties");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		httpServer.setUserRealms(new HashUserRealm[] { realm });

		logger.info("HttpServer启动成功.");

		this.mbeanServer = ManagementFactory.getPlatformMBeanServer();
		MBeanContainer mbContainer = new MBeanContainer(mbeanServer);
		httpServer.getServer().getContainer().addEventListener(mbContainer);
		// mServer.addBean(mbContainer);
		// mbContainer.addBean(Log.getLog());
		logger.info("MBean注册成功.");

		if (!httpServer.isRunning()) {
			try {
				httpServer.start();
				httpServer.join();// 阻塞
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("HttpServer关闭.");
		}
	}
}
