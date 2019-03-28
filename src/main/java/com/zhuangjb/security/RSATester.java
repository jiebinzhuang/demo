package com.zhuangjb.security;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class RSATester {
	public static final String KEY_ALGORITHM = "RSA";
	public static final int MAX_ENCRYPT_BLOCK = 117;
	public static final int MAX_DECRYPT_BLOCK = 128;
	public static String publicKey;
	public static String privateKey;
	public static final String OxCAFEBABE = "DGC_FAFD6D01A21F50344D3A443FFE059D1D6BEEA3AA1C7E567603E26D8A";

	public static byte[] decryptByPublicKey(byte[] encryptedData, RSAPublicKey rpk) throws Exception {
		byte[] keyBytes = Base64.decode(publicKey);

		keyBytes = rpk.getEncoded();
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(2, publicK);
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
}