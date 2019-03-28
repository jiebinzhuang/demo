package com.zhuangjb.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES-128-CBC加解密模式
 * 
 * @author tanyn
 */
public class AES21CNUtils {

	/**
	 * 加密
	 * 
	 * @param encData
	 *            要加密的数据
	 * @param secretKey
	 *            密钥 ,16位的数字和字母
	 * @param vector
	 *            初始化向量,16位的数字和字母
	 * @return
	 * @throws Exception
	 */
	public static String Encrypt(String encData, String secretKey, String vector) throws Exception {

		if (secretKey == null) {
			return null;
		}
		if (secretKey.length() != 16) {
			return null;
		}
		byte[] raw = secretKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
		IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(encData.getBytes());
		return encodeBytes(encrypted);
	}

	/**
	 * 解密
	 * 
	 * @param decData
	 * @param secretKey
	 *            密钥 ,16位的数字和字母
	 * @param vector
	 *            初始化向量,16位的数字和字母
	 * @return
	 * @throws Exception
	 */
	public static String Decrypt(String decData, String secretKey, String vector) throws Exception {

		if (secretKey == null) {
			return null;
		}
		if (secretKey.length() != 16) {
			return null;
		}
		byte[] raw = secretKey.getBytes("ASCII");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(vector.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		byte[] encrypted1 = decodeBytes(decData);
		byte[] original = cipher.doFinal(encrypted1);
		String originalString = new String(original);
		return originalString;
	}

	/**
	 * 转16进制
	 * 
	 * @param bytes
	 * @return
	 */
	public static String encodeBytes(byte[] bytes) {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
			strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
		}
		return strBuf.toString();
	}

	/**
	 * 转字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] decodeBytes(String str) {
		byte[] bytes = new byte[str.length() / 2];
		for (int i = 0; i < str.length(); i += 2) {
			char c = str.charAt(i);
			bytes[i / 2] = (byte) ((c - 'a') << 4);
			c = str.charAt(i + 1);
			bytes[i / 2] += (c - 'a');
		}
		return bytes;
	}

	public static void main(String[] args) throws Exception {
		String str = "gdgpgohehcgbgdheejgedncchegfhdhedcdadbdfdbdbdbdcdbdbdbdddedccccghagbhchegogfhceogpdnccdbdadadadfdedddhdecccghdgfgdhcgfelgfhjdncceidfghephddbfkhdgielfkdgfhgjgleocccghggfgdhegphcdnccdididididbdfdjdgdadbdbdfdcdfddddcccggbgdhegjhggjhehjejgednccdbdadadjdjdgcccggbgdhegjhggjhehjeogbgngfdncchegfhdhedcdadbdfdbdbdbdcdbdbdbdddedccccghagmgbheepgggggfhceogbgngfdnccohjelfoelpkbfpdfenofjllnofigifojiajkohjekiofimifcccghagmgbheepgggggfhcejgednccdbdadddadfdacccghdgfhchggjgdgfedgpgegfdnccegfddadadadbcc";
		String result = AES21CNUtils.Decrypt(str, "H5gOs1ZshKZ6WikN", "8888159601152533");
		System.out.println(result);
		// String data =
		// "{\"service_code\":\"FS0014\",\"phone_id\":\"18022887432\",\"start_time\":\"20141015000000\",\"end_time\":\"2014101610000000\"}";
		// String res = AES.Encrypt(data, "abcdefguhyjhddda",
		// "1234567098716351");
		// System.out.println(res);
	}

}
