package com.zhuangjb.security;

import java.security.MessageDigest;

/**
 * Java常用的对密码加密的方法 对摘要信息进行加密编码
 */
public class CodeEncoder {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/**
	 * 将字节数组转换为16进制的字符串
	 * 
	 * @param byteArray
	 *            字节数组
	 * @return 16进制的字符串
	 */
	private String byteArrayToHexString(byte[] byteArray) {
		StringBuffer sb = new StringBuffer();
		for (byte byt : byteArray) {
			sb.append(byteToHexString(byt));
		}
		return sb.toString();
	}

	/**
	 * 将字节转换为16进制字符串
	 * 
	 * @param byt
	 *            字节
	 * @return 16进制字符串
	 */
	private String byteToHexString(byte byt) {
		int n = byt;
		if (n < 0)
			n = 256 + n;
		return hexDigits[n / 16] + hexDigits[n % 16];
	}

	/**
	 * 将摘要信息转换为相应的编码
	 * 
	 * @param code
	 *            编码类型
	 * @param message
	 *            摘要信息
	 * @return 相应的编码字符串
	 */
	private String Encode(String code, String message) {
		MessageDigest md;
		String encode = null;
		try {
			md = MessageDigest.getInstance(code);
			// encode = new String(md.digest(message.getBytes()), "UTF-8");
			encode = byteArrayToHexString(md.digest(message.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encode;
	}

	/**
	 * 将摘要信息转换成MD5编码
	 * 
	 * @param message
	 *            摘要信息
	 * @return MD5编码之后的字符串
	 */
	public String md5Encode(String message) {
		return Encode("MD5", message);
	}

	/**
	 * 将摘要信息转换成SHA编码
	 * 
	 * @param message
	 *            摘要信息
	 * @return SHA编码之后的字符串
	 */
	public String shaEncode(String message) {
		return Encode("SHA", message);
	}

	/**
	 * 将摘要信息转换成SHA-256编码
	 * 
	 * @param message
	 *            摘要信息
	 * @return SHA-256编码之后的字符串
	 */
	public String sha256Encode(String message) {
		return Encode("SHA-256", message);
	}

	/**
	 * 将摘要信息转换成SHA-512编码
	 * 
	 * @param message
	 *            摘要信息
	 * @return SHA-512编码之后的字符串
	 */
	public String sha512Encode(String message) {
		return Encode("SHA-512", message);
	}

	public static void main(String[] args) {
		CodeEncoder cu = new CodeEncoder();
		// 对MD5进行验证
		System.out.println("----MD5----");
		System.out.println(cu.validate("d41d8cd98f00b204e9800998ecf8427e", cu.md5Encode("")));
		System.out.println(cu.validate("0cc175b9c0f1b6a831c399e269772661", cu.md5Encode("a")));
		System.out.println(cu.validate("900150983cd24fb0d6963f7d28e17f72", cu.md5Encode("abc")));
		System.out.println(cu.validate("f96b697d7cb7938d525a2f31aaf161d0", cu.md5Encode("message digest")));
		System.out.println(cu.validate("c3fcd3d76192e4007dfb496cca67e13b", cu.md5Encode("abcdefghijklmnopqrstuvwxyz")));
		System.out.println(cu.validate("d174ab98d277d9f5a5611c2c9f419d9f",
				cu.md5Encode("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789")));
		System.out.println(cu.validate("57edf4a22be3c955ac49da2e2107b67a",
				cu.md5Encode("12345678901234567890123456789012345678901234567890123456789012345678901234567890")));

		// 对SHA进行验证
		System.out.println("----SHA----");
		System.out.println(cu.validate("2fd4e1c67a2d28fced849ee1bb76e7391b93eb12",
				cu.shaEncode("The quick brown fox jumps over the lazy dog")));
		System.out.println(cu.validate("de9f2c7fd25e1b3afad3e85a0bd17d9b100db4b3",
				cu.shaEncode("The quick brown fox jumps over the lazy cog")));
		System.out.println(cu.validate("da39a3ee5e6b4b0d3255bfef95601890afd80709", cu.shaEncode("")));
		System.out.println("-----------");

		// 对于SHA-256，SHA-512请自行验证

		// 下面显示MD5 SHA SHA-256 SHA-512所生成的长度

		System.out.println("--MD5--:" + cu.md5Encode("test"));
		System.out.println("--SHA--:" + cu.shaEncode("test"));
		System.out.println("SHA-256:" + cu.sha256Encode("test"));
		System.out.println("SHA-512:" + cu.sha512Encode("test"));
	}

	private String validate(String code, String tag) {
		if (code.equals(tag))
			return "通过验证";
		return "未通过验证";
	}
}
