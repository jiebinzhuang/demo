package com.zhuangjb.security;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Hex;

/**
 * 
 * @since 1.0
 * @author Allen
 */
public final class AESUtils {

	private static final byte[] INIT_VECTOR = { 0x31, 0x37, 0x36, 0x35, 0x34, 0x33, 0x32, 0x31, 0x38, 0x27, 0x36, 0x35,
			0x33, 0x23, 0x32, 0x33 };

	/**
	 * Encrypt the content with a given key using aes algorithm.
	 * 
	 * @param content
	 * @param apiKey
	 *            must contain exactly 32 characters
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String content, String apiKey) throws Exception {
		if (apiKey == null) {
			throw new IllegalArgumentException("Key cannot be null!");
		}
		String encrypted = null;
		byte[] keyBytes = apiKey.getBytes();
		if (keyBytes.length != 32 && keyBytes.length != 24 && keyBytes.length != 16) {
			throw new IllegalArgumentException("Key length must be 128/192/256 bits!");
		}
		byte[] encryptedBytes = null;
		encryptedBytes = encrypt(content.getBytes(), keyBytes, INIT_VECTOR);
		encrypted = new String(Hex.encode(encryptedBytes));
		return encrypted;
	}

	/**
	 * Decrypt the content with a given key using aes algorithm.
	 * 
	 * @param content
	 * @param apiKey
	 *            must contain exactly 32 characters
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String content, String apiKey) throws Exception {
		if (apiKey == null) {
			throw new IllegalArgumentException("Key cannot be null!");
		}
		String decrypted = null;
		byte[] encryptedContent = Hex.decode(content);
		byte[] keyBytes = apiKey.getBytes();
		byte[] decryptedBytes = null;
		if (keyBytes.length != 32 && keyBytes.length != 24 && keyBytes.length != 16) {
			throw new IllegalArgumentException("Key length must be 128/192/256 bits!");
		}
		decryptedBytes = decrypt(encryptedContent, keyBytes, INIT_VECTOR);
		decrypted = new String(decryptedBytes);
		return decrypted;
	}

	/**
	 * Encrypt data.
	 * 
	 * @param plain
	 * @param key
	 * @param iv
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] plain, byte[] key, byte[] iv) throws Exception {
		PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESFastEngine()));
		CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key), iv);
		aes.init(true, ivAndKey);
		return cipherData(aes, plain);
	}

	/**
	 * Decrypt data.
	 * 
	 * @param cipher
	 * @param key
	 * @param iv
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] cipher, byte[] key, byte[] iv) throws Exception {
		PaddedBufferedBlockCipher aes = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESFastEngine()));
		CipherParameters ivAndKey = new ParametersWithIV(new KeyParameter(key), iv);
		aes.init(false, ivAndKey);
		return cipherData(aes, cipher);
	}

	/**
	 * Encrypt or decrypt data.
	 * 
	 * @param cipher
	 * @param data
	 * @return
	 * @throws Exception
	 */
	private static byte[] cipherData(PaddedBufferedBlockCipher cipher, byte[] data) throws Exception {
		int minSize = cipher.getOutputSize(data.length);
		byte[] outBuf = new byte[minSize];
		int length1 = cipher.processBytes(data, 0, data.length, outBuf, 0);
		int length2 = cipher.doFinal(outBuf, length1);
		int actualLength = length1 + length2;
		byte[] result = new byte[actualLength];
		System.arraycopy(outBuf, 0, result, 0, result.length);
		return result;
	}

	public static void main(String[] args) {
		try {
			String apiKey = "DkW5saY2PSQoplUiH2YmiXfMAKUJtzwU";
			String content = "test12345";
			long start = System.currentTimeMillis();
			String encrypt = encrypt(content, apiKey);
			System.out.println(System.currentTimeMillis() - start + " ms");
			System.out.println(encrypt);
			String decrypt = decrypt(encrypt, apiKey);
			System.out.println(decrypt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}