package com.zhuangjb.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class MobileLocationUtil {

	/**
	 * 获取URL返回的字符串
	 * 
	 * @param callurl
	 * @param charset
	 * @return
	 */
	private static String callUrlByGet(String callurl, String charset) {
		String result = "";
		try {
			URL url = new URL(callurl);
			URLConnection connection = url.openConnection();
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(10000);
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line;
			while ((line = reader.readLine()) != null) {
				result += line;
				result += "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return result;
	}

	/**
	 * 手机号码归属地
	 * 
	 * @param tel
	 *            手机号码
	 * @return 135XXXXXXXX,联通/移动/电信,湖北武汉
	 * @throws Exception
	 * @author
	 */
	public static String getMobileLocation(String tel) throws Exception {
		if (tel.length() < 11) {
			tel += "0001";
		}
		Pattern pattern = Pattern.compile("1\\d{10}");
		Matcher matcher = pattern.matcher(tel);
		if (matcher.matches()) {
			String url = "http://life.tenpay.com/cgi-bin/mobile/MobileQueryAttribution.cgi?chgmobile=" + tel;
			String result = callUrlByGet(url, "GBK");
			StringReader stringReader = new StringReader(result);
			InputSource inputSource = new InputSource(stringReader);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(inputSource);
			String retmsg = document.getElementsByTagName("retmsg").item(0).getFirstChild().getNodeValue();
			if (retmsg.equals("OK")) {
				String supplier = document.getElementsByTagName("supplier").item(0).getFirstChild().getNodeValue()
						.trim();
				String province = document.getElementsByTagName("province").item(0).getFirstChild().getNodeValue()
						.trim();
				String city = document.getElementsByTagName("city").item(0).getFirstChild().getNodeValue().trim();
				if (province.equals("-") || city.equals("-")) {
					// return (tel + "," + supplier + ","+
					// getLocationByMobile(tel));
					// return (getLocationByMobile(tel) + "," + supplier);
					return (province + "," + city + "," + supplier);
				} else {
					// return (tel + "," + supplier + ","+ province + city);
					return (province + "," + city + "," + supplier);
				}
			} else {
				return "无此号记录！";
			}
		} else {
			return tel + "：手机号码格式错误！";
		}

	}

	public static void main(String[] args) {
		try {
			System.out.println(MobileLocationUtil.getMobileLocation("13400688403"));
			// System.out.println(MobileLocationUtil.getLocationByMobile("13400688403"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}