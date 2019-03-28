package com.zhuangjb.util;

import java.security.MessageDigest;

/**
 * MD5编码器 2011-4-9
 * 
 * @author fangw
 */
public class MD5 {
	// 用来将字节转换成 16 进制表示的字xx
	public static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 进行MD5加密
	 * 
	 * @param inStr
	 * @return
	 */
	public String encrypt(String inStr) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(inStr.getBytes());
			return bytetoString(digest);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String encrypt2(String inStr) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(inStr.getBytes("UTF-8"));
			return Base64Util.encode(digest);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 进行MD5加密
	 * 
	 * @param inStr
	 * @return
	 */
	public String encryptByRule1(String inStr, String appKey) {
		return encrypt(inStr.replace("&", "") + appKey).toUpperCase();
	}

	public String bytetoString(byte[] digest) {
		char str[] = new char[16 * 2]; // 每个字节xxx16
		int k = 0; // 表示转换结果中对应的字符位置
		for (int i = 0; i < 16; i++) {
			byte byte0 = digest[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf]; // 取字节中xxx4 位的数字转换
		}

		return new String(str); // 换后的结果转换为字符xxx
	}

	public static void main(String[] args) {
		// String aa ="7ccb0eea8a706c4c34a16891f84e7b";
		MD5 md5 = new MD5();
		String aa1 = md5.encrypt("aa");
		System.out.println(aa1.length());
		System.out.println(aa1);

		System.out.println(md5.encrypt("0"));
		System.out.println(md5.encrypt("1"));
		System.out.println(md5.encrypt("2"));
	}
}