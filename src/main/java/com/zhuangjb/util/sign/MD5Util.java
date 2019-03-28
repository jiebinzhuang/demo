package com.zhuangjb.util.sign;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * 功能：支付宝MD5签名处理核心文件，不需要修改 版本：3.3 修改日期：2012-08-17 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究支付宝接口使用，只是提供一个
 */

public class MD5Util {

	public static String md5(String content) {
		return DigestUtils.md5Hex(getContentBytes(content, "UTF-8"));
	}

	public static String md5(String content, String input_charset) {
		return DigestUtils.md5Hex(getContentBytes(content, input_charset));
	}

	/**
	 * 签名字符串
	 *
	 * @param content
	 *            需要签名的字符串
	 * @param key
	 *            密钥
	 * @param input_charset
	 *            编码格式
	 * @return 签名结果
	 */
	public static String sign(String content, String key, String input_charset) {
		content = content + key;
		return md5(content, input_charset);
	}

	/**
	 * 签名字符串
	 *
	 * @param content
	 *            需要签名的字符串
	 * @param sign
	 *            签名结果
	 * @param key
	 *            密钥
	 * @param input_charset
	 *            编码格式
	 * @return 签名结果
	 */
	public static boolean verify(String content, String sign, String key, String input_charset) {
		content = content + key;
		String mySign = md5(content, input_charset);

		if (mySign.equals(sign)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param content
	 * @param charset
	 * @return
	 * @throws java.security.SignatureException
	 *
	 * @throws java.io.UnsupportedEncodingException
	 *
	 */
	private static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
	}

}