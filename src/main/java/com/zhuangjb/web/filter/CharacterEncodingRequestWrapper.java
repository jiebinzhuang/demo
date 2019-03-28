package com.zhuangjb.web.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.zhuangjb.util.WebUtils;

/**
 * 编码过滤器
 * 
 * @author fangw
 */
public class CharacterEncodingRequestWrapper extends HttpServletRequestWrapper {

	public CharacterEncodingRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}

	@Override
	public String[] getParameterValues(String name) {
		if (super.getMethod() != null && super.getMethod().equalsIgnoreCase("get")) {
			// GET方式，判断是否需要转码(应用服务器URI编码默认使用ISO-8859-1，这样会乱码)
			return this.getParametersForceEncode(name);
		} else {
			/**
			 * 对post提交时的get方式传递的参数，也要做编码转换
			 */
			if (super.getQueryString() != null && super.getQueryString().toString().contains(name + "=")) {
				return this.getParametersForceEncode(name);
			} else {
				return super.getParameterValues(name);
			}
		}
	}

	@Override
	public String getParameter(String name) {
		if (super.getMethod() != null && super.getMethod().equalsIgnoreCase("get")) {
			// GET方式，判断是否需要转码(应用服务器URI编码默认使用ISO-8859-1，这样会乱码)
			return this.getParameterForceEncode(name);
		} else {
			/**
			 * 对post提交时的get方式传递的参数，也要做编码转换
			 */
			if (super.getQueryString() != null && super.getQueryString().toString().contains(name + "=")) {
				return this.getParameterForceEncode(name);
			} else {
				return super.getParameter(name);
			}
		}
	}

	/**
	 * 获取request的参数，这里做强制编码转换
	 * 
	 * <pre>
	 * 为什么要用此方法，因为当post提交数据时，有些参数是在url中用get方式传递的，此时编码过滤器对POST时的参数都未做处理，就会导致乱码
	 * </pre>
	 * 
	 * @param request
	 * @param param
	 * @return
	 */
	public String getParameterForceEncode(String param) {
		String value = super.getParameter(param);

		if (value != null) {
			String encodeGetParameter = null;
			// TOMCAT默认转码，WAS等默认不转码
			if (WebUtils.getServerInfo().contains("Tomcat")) {
				encodeGetParameter = WebUtils.getInitParameter(this.getContextPath(), "encodeGetParameter", "true");
			} else {
				encodeGetParameter = WebUtils.getInitParameter(this.getContextPath(), "encodeGetParameter", "false");
			}
			// request的encodeGetParameter参数最优先
			if (super.getParameter("_encodeGetParameter") != null) {
				encodeGetParameter = super.getParameter("_encodeGetParameter");
			}

			if (encodeGetParameter != null && "true".equalsIgnoreCase(encodeGetParameter)) {
				try {
					String sourceEncode = WebUtils.getInitParameter(this.getContextPath(), "encodeGetParameter_source",
							"ISO-8859-1");
					if (super.getParameter("_sourceEncode") != null) {
						sourceEncode = super.getParameter("_sourceEncode");
					}
					String targetEncode = WebUtils.getInitParameter(this.getContextPath(), "encodeGetParameter_target",
							"UTF-8");
					if (super.getParameter("_targetEncode") != null) {
						targetEncode = super.getParameter("_targetEncode");
					}
					value = new String(value.getBytes(sourceEncode), targetEncode);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}

		return value;
	}

	/**
	 * 获取request的参数，这里做强制编码转换
	 * 
	 * <pre>
	 * 为什么要用此方法，因为当post提交数据时，有些参数是在url中用get方式传递的，此时编码过滤器对POST时的参数都未做处理，就会导致乱码
	 * </pre>
	 * 
	 * @param param
	 * @return
	 */
	public String[] getParametersForceEncode(String param) {
		String[] value = super.getParameterValues(param);

		if (value != null && value.length > 0) {
			String encodeGetParameter = null;
			// TOMCAT默认转码，WAS等默认不转码
			if (WebUtils.getServerInfo().contains("Tomcat")) {
				encodeGetParameter = WebUtils.getInitParameter(this.getContextPath(), "encodeGetParameter", "true");
			} else {
				encodeGetParameter = WebUtils.getInitParameter(this.getContextPath(), "encodeGetParameter", "false");
			}
			// super的encodeGetParameter参数最优先
			if (super.getParameter("_encodeGetParameter") != null) {
				encodeGetParameter = super.getParameter("_encodeGetParameter");
			}

			if (encodeGetParameter != null && "true".equalsIgnoreCase(encodeGetParameter)) {
				try {
					String sourceEncode = WebUtils.getInitParameter(this.getContextPath(), "encodeGetParameter_source",
							"ISO-8859-1");
					if (super.getParameter("_sourceEncode") != null) {
						sourceEncode = super.getParameter("_sourceEncode");
					}
					String targetEncode = WebUtils.getInitParameter(this.getContextPath(), "encodeGetParameter_target",
							"UTF-8");
					if (super.getParameter("_targetEncode") != null) {
						targetEncode = super.getParameter("_targetEncode");
					}
					for (int i = 0; i < value.length; i++) {
						value[i] = new String(value[i].getBytes(sourceEncode), targetEncode);
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}

		return value;
	}
}
