package com.zhuangjb.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.io.SAXReader;

import com.zhuangjb.web.listener.AppClientListener;


/**
 * <b>WEB工具类</b><br>
 * 
 * @author fangw
 * @date 2010-10-25
 */
public class WebUtils {
	private static final Log log = LogFactory.getLog(WebUtils.class);
	private static String serverInfo = null;

	public static String getServerInfo() {
		return serverInfo;
	}

	public static void setServerInfo(String serverInfo) {
		WebUtils.serverInfo = serverInfo;
	}

	@SuppressWarnings("unused")
	private static SAXReader getSAXReaderInstance(String encoding) {
		if (encoding == null) {
			encoding = "GBK";
		}
		SAXReader saxReader = new SAXReader();
		saxReader.setEncoding(encoding);
		saxReader.setIgnoreComments(true);
		return saxReader;
	}

	/**
	 * 获取当前应用的上下文(非安全方式，因为默认并没有暴露相关接口，所以采用类反射强行获取)
	 * 
	 * @param sce
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getContextPath(ServletContextEvent sce) {
		String ctxPath = null;
		if (sce.getServletContext().getServerInfo().contains("jetty")) {
			try {
				Object obj = sce.getServletContext().getRequestDispatcher("/");


			} catch (Exception e) {
				throw new RuntimeException("获取上下文失败!");
			}
			if (ctxPath == null || ctxPath.length() == 0) {
				ctxPath = "/";
			}
			return ctxPath;
		}

		try {
			// tomcat6和was6.1已测试可行，但tomcat5.5-报错，针对tomcat5.5-采用了另外的获取方式
			Object servletContext = sce.getServletContext();
			Class servletContextClass = servletContext.getClass();
			java.lang.reflect.Field contextField = servletContextClass.getDeclaredField("context");
			contextField.setAccessible(true);
			Object context = contextField.get(servletContext);

			Class contextClass = context.getClass();
			java.lang.reflect.Method getContextPath = contextClass.getMethod("getContextPath");
			Object contextPath = getContextPath.invoke(context);

			ctxPath = contextPath.toString();
			log.info("contextPath=" + contextPath);
		} catch (Exception e) {
			try {
				// tomcat5.5-采用这种方式，上面那种会报错
				Object obj = sce.getServletContext().getRequestDispatcher("/");
				log.info("contextPath(Maybe running in tomcat5.5-)=" + ctxPath);
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new RuntimeException("获取上下文失败!");
			}
		}
		return ctxPath;
	}

	/**
	 * Return the real path of the given path within the web application, as
	 * provided by the servlet container.
	 * <p>
	 * Prepends a slash if the path does not already start with a slash, and
	 * throws a FileNotFoundException if the path cannot be resolved to a
	 * resource (in contrast to ServletContext's <code>getRealPath</code>, which
	 * returns null).
	 * 
	 * @param servletContext
	 *            the servlet context of the web application
	 * @param path
	 *            the path within the web application
	 * @return the corresponding real path
	 * @throws FileNotFoundException
	 *             if the path cannot be resolved to a resource
	 * @see javax.servlet.ServletContext#getRealPath
	 */
	public static String getRealPath(ServletContext servletContext, String path) throws FileNotFoundException {
		// Interpret location as relative to the web application root directory.
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		String realPath = servletContext.getRealPath(path);
		if (realPath == null) {
			throw new FileNotFoundException("ServletContext resource [" + path
					+ "] cannot be resolved to absolute file path - " + "web application archive not expanded?");
		}
		return realPath;
	}

	public static String getInitParameter(String contextPath, String name) {
		if (contextPath == null || contextPath.length() == 0) {
			contextPath = "/";
		}
		return AppClientListener.getInitParameters().get(contextPath).get(name);
	}

	public static String getInitParameter(String contextPath, String name, String defaultValue) {
		if (contextPath == null || contextPath.length() == 0) {
			contextPath = "/";
		}
		if (AppClientListener.getInitParameters().get(contextPath).containsKey(name)
				&& AppClientListener.getInitParameters().get(contextPath).get(name) != null) {
			return getInitParameter(contextPath, name);
		}
		return defaultValue;
	}

	/**
	 * 获取客户端ip，这里处理了二级代理、反向代理等情况
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 反序列化对象
	 * 
	 * @param input
	 * @return
	 */
	public static Object deserializObject(InputStream input) {
		try {
			ObjectInputStream responseReader = new ObjectInputStream(input);
			return responseReader.readObject();
		} catch (Exception e) {
			log.error("反序列化对象时报错" + e.getMessage(), e);
			throw new RuntimeException("反序列化对象时报错" + e.getMessage(), e);
		}
	}

	/**
	 * 序列化对象
	 * 
	 * @param data
	 * @return
	 */
	public static ByteArrayOutputStream serializObject(Object data) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutStream;
		try {
			objectOutStream = new ObjectOutputStream(outStream);
			objectOutStream.writeObject(data);
			objectOutStream.close();
		} catch (Exception e) {
			log.error("序列化对象时报错" + e.getMessage());
			return null;
		}
		return outStream;
	}

	/**
	 * 获取request的参数，这里做强制编码转换
	 * 
	 * <pre>
	 * 为什么要用此方法，因为当post提交数据时，有些参数是在url中用get方式传递的，此时编码过滤器对POST时的参数都未做处理，就会导致乱码
	 * </pre>
	 * 
	 * @param request
	 * @param parameter
	 * @return
	 */
	public static String getParameter(HttpServletRequest request, String parameter) {
		String value = request.getParameter(parameter);

		if (value != null) {
			String encodeGetParameter = null;
			// TOMCAT默认转码，WAS等默认不转码
			if (WebUtils.getServerInfo().contains("Tomcat")) {
				encodeGetParameter = WebUtils.getInitParameter(request.getContextPath(), "encodeGetParameter", "true");
			} else {
				encodeGetParameter = WebUtils.getInitParameter(request.getContextPath(), "encodeGetParameter", "false");
			}
			// request的encodeGetParameter参数最优先
			if (request.getParameter("_encodeGetParameter") != null) {
				encodeGetParameter = request.getParameter("_encodeGetParameter");
			}

			if (encodeGetParameter != null && "true".equalsIgnoreCase(encodeGetParameter)) {
				try {
					String sourceEncode = WebUtils.getInitParameter(request.getContextPath(),
							"encodeGetParameter_source", "ISO-8859-1");
					if (request.getParameter("_sourceEncode") != null) {
						sourceEncode = request.getParameter("_sourceEncode");
					}
					String targetEncode = WebUtils.getInitParameter(request.getContextPath(),
							"encodeGetParameter_target", "UTF-8");
					if (request.getParameter("_targetEncode") != null) {
						targetEncode = request.getParameter("_targetEncode");
					}
					value = new String(value.getBytes(sourceEncode), targetEncode);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}

		return value;
	}

}
