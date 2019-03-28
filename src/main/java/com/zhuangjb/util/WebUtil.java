package com.zhuangjb.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class WebUtil {

	/**
	 * 字符串编码
	 *
	 * @param sStr
	 * @param sEnc
	 * @return String
	 */
	public final static String UrlEncoder(String sStr, String sEnc) {
		String sReturnCode = "";
		try {
			sReturnCode = URLEncoder.encode(sStr, sEnc);
		} catch (UnsupportedEncodingException ex) {

		}
		return sReturnCode;
	}

	public final static String UrlEncoder(String sStr) {
		return UrlEncoder(sStr, "UTF-8");
	}

	public final static String UrlDecoder(String sStr) {
		return UrlDecoder(sStr, "UTF-8");
	}

	/**
	 * 字符串解码
	 *
	 * @param sStr
	 * @param sEnc
	 * @return String
	 */
	public final static String UrlDecoder(String sStr, String sEnc) {
		String sReturnCode = "";
		try {
			sReturnCode = URLDecoder.decode(sStr, sEnc);
		} catch (UnsupportedEncodingException ex) {

		}
		return sReturnCode;
	}
}
