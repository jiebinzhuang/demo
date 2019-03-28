package com.zhuangjb.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpUtils {
	public static final Log log = LogFactory.getLog(HttpUtils.class);

	public static String httpGet(String url, String charset) throws Exception {
		log.info(url);
		URL uri = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
		try {
			conn.setRequestProperty("Accept-Charset", charset);// UTF-8/GBK
			conn.setRequestMethod("GET");

			InputStream is = conn.getInputStream();

			// 获取响应数据
			StringBuffer contentBuffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
			String inputLine = null;
			while ((inputLine = reader.readLine()) != null) {
				if (contentBuffer.length() > 0) {
					contentBuffer.append("\n");
				}
				contentBuffer.append(inputLine);
			}
			is.close();

			int statusCode = conn.getResponseCode();
			if (statusCode == HttpStatus.SC_OK) {
				String str = contentBuffer.toString();
				log.info("httpGet url=" + url + "\ncharset=" + charset + "\nresult=" + str);
				// System.out.println(contentBuffer.toString());
				return contentBuffer.toString();
			} else {
				// String str = contentBuffer.toString();
				// log.info("httpGet url=" + url + "\ncharset=" + charset +
				// "\nresult=" + str);
				// throw new RuntimeException("HTTP调用出错,状态码:" + statusCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}
		return null;
	}

	public static String httpGet(String url) throws Exception {
		return httpGet(url, "UTF-8");
	}

	public static String httpPost(String url, NameValuePair[] data) throws Exception {
		return httpPost(url, data, null);
	}

	public static String httpPost(String url, String data) throws Exception {
		return httpPost(url, data, null);
	}

	public static String httpPost(String url, NameValuePair[] data, String charSet) throws Exception {
		log.info(url);
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
		PostMethod postMethod = null;
		try {
			postMethod = new PostMethod(url);
			// 填入各个表单域的值
			// NameValuePair[] data = { new NameValuePair("action", "login"),
			// new
			// NameValuePair("username", "admin"),
			// new NameValuePair("password", "admin") };
			// 将表单的值放入postMethod中
			postMethod.setRequestBody(data);
			// 执行postMethod
			int statusCode = httpClient.executeMethod(postMethod);
			log.info(" status code:" + statusCode);

			if (statusCode == HttpStatus.SC_OK) {
				StringBuffer contentBuffer = new StringBuffer();
				InputStream in = postMethod.getResponseBodyAsStream();
				if (charSet == null) {
					charSet = postMethod.getResponseCharSet();
				}
				BufferedReader reader = new BufferedReader(new InputStreamReader(in, charSet));
				String inputLine = null;
				while ((inputLine = reader.readLine()) != null) {
					if (contentBuffer.length() > 0) {
						contentBuffer.append("\n");
					}
					contentBuffer.append(inputLine);
					// System.out.println("input line:" + inputLine);
				}
				in.close();
				log.info("httpPost url=" + url + "\nresult=" + contentBuffer.toString());
				return contentBuffer.toString();
			} else if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
				// 从头中取出转向的地址
				// Header locationHeader =
				// postMethod.getResponseHeader("location");
				// String location = null;
				// if (locationHeader != null) {
				// location = locationHeader.getValue();
				// System.out.println("The page was redirected to:" + location);
				// } else {
				// System.err.println("Location field value is null.");
				// }
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			postMethod.releaseConnection();
		}
		return null;
	}

	public static String httpPost(String url, String data, String charSet) throws Exception {
		return httpPost(url, data, charSet, null);
	}

	public static String httpPost(String url, String data, String charSet, Map<String, String> headerMap)
			throws Exception {
		log.info(url);
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
		PostMethod postMethod = null;
		try {
			postMethod = new PostMethod(url);
			// 填入各个表单域的值
			// NameValuePair[] data = { new NameValuePair("action", "login"),
			// new
			// NameValuePair("username", "admin"),
			// new NameValuePair("password", "admin") };
			// 将表单的值放入postMethod中
			if (data != null) {
				postMethod.setRequestBody(data);
			}
			if (headerMap != null && !headerMap.isEmpty()) {
				for (String key : headerMap.keySet()) {
					postMethod.addRequestHeader(key, headerMap.get(key));
				}
			}
			// 执行postMethod
			int statusCode = httpClient.executeMethod(postMethod);
			log.info(" status code:" + statusCode);

			// if (statusCode == HttpStatus.SC_OK) {
			StringBuffer contentBuffer = new StringBuffer();
			InputStream in = postMethod.getResponseBodyAsStream();
			if (charSet == null) {
				charSet = postMethod.getResponseCharSet();
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, charSet));
			String inputLine = null;
			while ((inputLine = reader.readLine()) != null) {
				if (contentBuffer.length() > 0) {
					contentBuffer.append("\n");
				}
				contentBuffer.append(inputLine);
				// System.out.println("input line:" + inputLine);
			}
			in.close();
			log.info("httpPost url=" + url + "\nresult=" + contentBuffer.toString());
			return contentBuffer.toString();
			// } else if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY ||
			// statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
			// // 从头中取出转向的地址
			// // Header locationHeader =
			// // postMethod.getResponseHeader("location");
			// // String location = null;
			// // if (locationHeader != null) {
			// // location = locationHeader.getValue();
			// // System.out.println("The page was redirected to:" + location);
			// // } else {
			// // System.err.println("Location field value is null.");
			// // }
			// }
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			postMethod.releaseConnection();
		}
		return null;
	}
}
