package com.zhuangjb.security;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import javax.crypto.Cipher;

public class RSATest {
	public static final String KEY_ALGORITHM = "RSA";
	public static final int MAX_ENCRYPT_BLOCK = 117;
	public static final int MAX_DECRYPT_BLOCK = 128;

	public static void main(String[] args) {
	}

	public static void decodexx(String inEn) {
		try {
			byte[] ary_en = decodeBase64(inEn);
			System.out.println(ary_en.length);
			for (byte b : ary_en) {
				System.out.print(b);
			}
			System.out.println();
			byte[] deBytes = decryptByPrivateKey(ary_en);
			String s = new String(deBytes);
			System.out.println("解密结果为：" + s);
		} catch (Exception e) {
			System.out.println("\n解密失败：" + e.getMessage());
		}
	}

	public static byte[] decryptByPrivateKey(byte[] encryptedData) throws Exception {
		HashMap pkMap = rsaParameters("");

		PrivateKey pkey = getPrivateKey((String) pkMap.get("mudulus"), (String) pkMap.get("exponent"));

		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(2, pkey);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;

		int i = 0;

		while (inputLen - offSet > 0) {
			byte[] cache;
			if (inputLen - offSet > 128)
				cache = cipher.doFinal(encryptedData, offSet, 128);
			else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * 128;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	public static HashMap<String, String> rsaParameters(String xmlPublicKey) throws MalformedURLException {
		HashMap map = new HashMap();
		String mudulus = "3A5Rq+zMVwfifRO8vRQSjA/Z2/G3gL0OJGRaQ4rK4JswY2hWXD+/J6mM3WcWq/tz4qvDoV+Ige19l3ZbD4QxjwCeaQbgeBhEyRCokSNYumGNMi9PdBOY09WLz7q81T7WGQyuxAwPuRt2lmmAOF8sK9RpDchWToz99dTmwwmLHtM=";
		String exponent = "AQAB";
		map.put("mudulus", mudulus);
		map.put("exponent", exponent);
		return map;
	}

	public static byte[] decodeBase64(String input) throws Exception {
		Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		Method mainMethod = clazz.getMethod("decode", new Class[] { String.class });
		mainMethod.setAccessible(true);
		Object retObj = mainMethod.invoke(null, new Object[] { input });
		return (byte[]) retObj;
	}

	public static PublicKey getPublicKey(String modulus, String exponent) {
		try {
			byte[] m = decodeBase64(modulus);
			byte[] e = decodeBase64(exponent);
			BigInteger b1 = new BigInteger(1, m);
			BigInteger b2 = new BigInteger(1, e);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static PrivateKey getPrivateKey(String modulus, String exponent) {
		try {
			byte[] m = removeMSZero(decodeBase64(modulus));
			byte[] e = removeMSZero(decodeBase64(exponent));

			m = removeMSZero(modulus.getBytes());
			e = removeMSZero(exponent.getBytes());

			BigInteger b1 = new BigInteger(1, m);
			BigInteger b2 = new BigInteger(1, e);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(b1, b2);

			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] removeMSZero(byte[] data) {
		int len = data.length;
		byte[] data1;
		if (data[0] == 0) {
			data1 = new byte[data.length - 1];
			System.arraycopy(data, 1, data1, 0, len - 1);
		} else {
			data1 = data;
		}
		return data1;
	}
}