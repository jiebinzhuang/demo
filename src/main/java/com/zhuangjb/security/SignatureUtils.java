/*
 * Copyright 2014 Mopote.com
 *
 * The Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.zhuangjb.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.util.encoders.Hex;

/**
 * 
 * @since 1.0
 * @author Allen
 */
public final class SignatureUtils {

	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	private static final String ENCRYPTION_ALGORITHM = "SHA-1";
	private static final boolean DEFAULT_PUB = true;

	private SignatureUtils() {
	}

	public static String toHex(byte[] data) {
		return new String(Hex.encode(data));
	}

	public static String bytesToHexString(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}

	public static byte[] hexStringToBytes(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public static String digest(String strSrc) {
		return digest(strSrc, DEFAULT_PUB);
	}

	public static String digest(String strSrc, boolean pub) {
		return digest(strSrc, "MD5", pub);
	}

	public static String digest(String strSrc, String encName) {
		return digest(strSrc, encName, DEFAULT_PUB);
	}

	/**
	 * 使用指定算法生成消息摘要，默认是md5
	 * 
	 * @param strSrc
	 *            , a string will be encrypted; <br/>
	 * @param encName
	 *            , the algorithm name will be used, dafault to "MD5"; <br/>
	 * @return
	 */
	public static String digest(String strSrc, String encName, boolean pub) {
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = strSrc.getBytes();
		try {
			if (encName == null || encName.equals("")) {
				encName = "MD5";
			}
			md = MessageDigest.getInstance(encName);
			md.update(bt);
			byte[] digest = md.digest();
			strDes = (pub) ? toHex(digest) : bytesToHexString(digest);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Invalid algorithm: " + encName);
		}
		return strDes;
	}

	public static String signature(String appId, String key, String digest, long millis) {
		return signature(appId, key, digest, millis, DEFAULT_PUB);
	}

	public static String signature(String appId, String key, String digest, long millis, boolean pub) {
		String timestamp = String.valueOf(millis);
		String signature = null;
		if (isNotBlank(key) && isNotBlank(timestamp) && isNotBlank(appId)) {
			String value = SignatureArithmeticUtils.get(appId, key, digest, String.valueOf(millis));
			signature = digest(value, ENCRYPTION_ALGORITHM, pub);
		}
		return signature;
	}

	public static boolean isValidate(String signature, String appId, String secretKey, String digest, long millis) {
		return isValidate(signature, appId, secretKey, digest, millis, DEFAULT_PUB);
	}

	public static boolean isValidate(String signature, String appId, String secretKey, String digest, long millis,
			boolean pub) {
		String calculatedSignature = signature(appId, secretKey, digest, millis, pub);
		return isBlank(calculatedSignature) ? false : (calculatedSignature.equals(signature));
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out
				.println(digest("fea61ddef821005a08b4fac3502723d8643bcbbb53178f2a77ff676f3e21a3695d32fbb55734753b356aa52f52d9e6b3094b74aaf0490faaa94d70d69549fffa2637c3b6cae017e05488bf66618c8768662eaa8cdfce0749d228e7d241634c4c0c39844d032234337d7ce5bcaa11af72ed76166560a2d123213f3fb692cc87b3ea8ae1c46a775317e71bb3e613a75ec3134f8f00293c4cb3e6f149426d8d60fa9fd63d854f4048821a12a498c5648c74a1c45f4e0e1f725d478d93a724cf98a8ed2dee6b6b0c5da2dd0843cfca8d2d2c7ed42d0d80c59783843eed432fe88f25"));
		System.out.println(signature("100004", "T0375gFrk6uEyz2l", "4c47b122ebb31a532569ce04ffa1bb8d", 1426124089397l));
	}
}
