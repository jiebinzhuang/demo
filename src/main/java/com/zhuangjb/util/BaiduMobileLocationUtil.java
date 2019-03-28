package com.zhuangjb.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpStatus;
import org.nutz.json.Json;

public class BaiduMobileLocationUtil {

	public static String get(String tel) throws Exception {
		String url = "http://apis.baidu.com/apistore/mobilephoneservice/mobilephone?tel=";
		URL uri = new URL(url + tel);
		HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
		try {
			conn.setRequestProperty("Accept-Charset", "UTF-8");// UTF-8/GBK
			conn.setRequestProperty("apikey", "ae6c6a0a869b484c71c2ad66f3ceef8a");// UTF-8/GBK
			conn.setRequestMethod("GET");
			int statusCode = conn.getResponseCode();

			InputStream is = conn.getInputStream();

			// 获取响应数据
			StringBuffer contentBuffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String inputLine = null;
			while ((inputLine = reader.readLine()) != null) {
				if (contentBuffer.length() > 0) {
					contentBuffer.append("\n");
				}
				contentBuffer.append(inputLine);
			}
			is.close();

			if (statusCode == HttpStatus.SC_OK) {
				String str = contentBuffer.toString();
				// System.out.println(contentBuffer.toString());
				// return contentBuffer.toString();

				Map<String, Object> map = Json.fromJson(HashMap.class, str);
				if (map.containsKey("retData")) {
					Map map2 = (Map) map.get("retData");
					// System.out.println(map2.get("province"));
					// System.out.println(map2.get("carrier"));
					return map2.get("province") + "," + map2.get("carrier");
				}

			} else {
				// throw new RuntimeException("HTTP调用出错,状态码:" + statusCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			String str = BaiduMobileLocationUtil.get("13845504604");
			System.out.println(str);
//			Map<String, Object> map = Json.fromJson(HashMap.class, str);
//			Map map2 = (Map) map.get("retData");
//
//			System.out.println(map2.get("province"));
//			System.out.println(map2.get("carrier"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}