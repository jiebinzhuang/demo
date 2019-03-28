package com.zhuangjb.security;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.zhuangjb.busy.DBC;

/**
 * 字符x DESede(3DES) 加密 <br>
 * 加密算法,可用 DES,DESede,Blowfish
 */
public class ThreeDesUtils {
	/**
	 * 定义 加密算法,可用 DES,DESede,Blowfish
	 */
	private static final String ALGORITHM = "DESede";

	public static String encrypt(String txt) {
		return encrypt(DBC.SECRET_KEY, txt);
	}

	public static String decrypt(String txt) {
		return decrypt(DBC.SECRET_KEY, txt);
	}

	public static String encrypt(String key, String txt) {
		byte[] byteArr = ThreeDesUtils.encryptMode(key.getBytes(), txt.getBytes());
		return byteArr2HexStr(byteArr);
	}

	public static String decrypt(String key, String txt) {
		byte[] byteArr = ThreeDesUtils.decryptMode(key.getBytes(), hexStr2ByteArr(txt));
		return new String(byteArr);
	}

	/**
	 * @param key
	 *            为加密密钥，长度x4字节
	 * @param src
	 *            被加密的数据缓冲区（源）
	 */
	public static byte[] encryptMode(byte[] key, byte[] src) {
		try {
			// 生成密钥
			SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
			// 加密
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return cipher.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * @param key
	 *            为加密密钥，长度x4字节
	 * @param src
	 *            加密后的缓冲x
	 */
	public static byte[] decryptMode(byte[] key, byte[] src) {
		try {
			// 生成密钥
			SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
			// 解密
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return cipher.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// /**
	// * 转换成十六进制字符串
	// */
	// public static String byte2hex(byte[] byteArr) {
	// String hexStr = "";
	// String tmpStr = "";
	// for (int n = 0; n < byteArr.length; n++) {
	// tmpStr = (java.lang.Integer.toHexString(byteArr[n] & 0XFF));
	// if (tmpStr.length() == 1)
	// hexStr = hexStr + "0" + tmpStr;
	// else
	// hexStr = hexStr + tmpStr;
	// if (n < byteArr.length - 1)
	// hexStr = hexStr + ":";
	// }
	// return hexStr.toUpperCase();
	// }

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>
	 */
	public static byte[] hexStr2ByteArr(String strIn) {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 */
	public static String byteArr2HexStr(byte[] arrB) {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍..一个byte转成16进制最多不会超过两位。FF
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16)); // 16代表进制
		}
		return sb.toString();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		// 添加新安全算x如果用JCE就要把它添加进去
		// websphere下可能没有SunJCE

		// Security.addProvider(new com.sun.crypto.provider.SunJCE());
		byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB,
				(byte) 0xDD, 0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36, (byte) 0xE2 };
		String key = "zhuangjb.name1234567890!@";// 24位
		keyBytes = key.getBytes();
		// 24字节的密x
		String szSrc = "This is a 3DES test. 测试";
		System.out.println("字符:" + szSrc);
		byte[] encoded = ThreeDesUtils.encryptMode(keyBytes, szSrc.getBytes("gbk"));
		String s = byteArr2HexStr(encoded);
		System.out.println("加密:" + s);
		String str = URLEncoder.encode(s, "GBK");
		System.out.println("编码:" + str);
		System.out.println("编码长度:" + str.length());

		String str3 = URLDecoder.decode(str, "GBK");
		byte[] srcBytes2 = ThreeDesUtils.decryptMode(keyBytes, hexStr2ByteArr(str3));
		if (srcBytes2 == null) {
			System.out.println("srcBytes2==null");
			return;
		}
		System.out.println("解密:" + (new String(srcBytes2, "gbk")));
	}
}
