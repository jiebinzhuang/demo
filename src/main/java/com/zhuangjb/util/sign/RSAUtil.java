package com.zhuangjb.util.sign;

import sun.misc.BASE64Decoder;
import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("restriction")
public class RSAUtil {
	/** */
	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/** */
	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;
	/** */
	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";

	/** */
	/**
	 * 获取公钥的key
	 */
	private static final String PUBLIC_KEY = "RSAPublicKey";

	/** */
	/**
	 * 获取私钥的key
	 */
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	/**
	 * RSA签名
	 *
	 * @param content
	 *            待签名数据
	 * @param privateKey
	 *            商户私钥
	 * @param input_charset
	 *            编码格式
	 * @return 签名值
	 */
	public static String sign(String content, String privateKey, String input_charset) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64Util.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance(KEY_ALGORITHM);
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

			signature.initSign(priKey);
			signature.update(content.getBytes(input_charset));

			byte[] signed = signature.sign();

			return Base64Util.encode(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * RSA验签名检查
	 *
	 * @param content
	 *            待签名数据
	 * @param sign
	 *            签名值
	 * @param public_key
	 *            公钥
	 * @param input_charset
	 *            编码格式
	 * @return 布尔值
	 */
	public static boolean verify(String content, String sign, String public_key, String input_charset) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			byte[] encodedKey = Base64Util.decode(public_key);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

			java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			signature.update(content.getBytes(input_charset));

			boolean bverify = signature.verify(Base64Util.decode(sign));
			return bverify;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/************************************************************************/
	/**
	 * <p>
	 * 生成密钥对(公钥和私钥)
	 * </p>
	 *
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/** */
	/**
	 * <p>
	 * 获取私钥
	 * </p>
	 *
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return Base64Util.encode(key.getEncoded());
	}

	/** */
	/**
	 * <p>
	 * 获取公钥
	 * </p>
	 *
	 * @param keyMap
	 *            密钥对
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return Base64Util.encode(key.getEncoded());
	}

	/** */
	/**
	 * <P>
	 * 私钥解密
	 * </p>
	 *
	 * @param encryptedData
	 *            已加密数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
		byte[] keyBytes = Base64Util.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	public static String decryptByPrivateKey(String encryptedData, String privateKey) throws Exception {
		return new String(decryptByPrivateKey((new BASE64Decoder()).decodeBuffer(encryptedData), privateKey));
	}

	public static byte[] encryptByCertificate(byte[] data, String certificateData) throws Exception {
		byte[] keyBytes = Base64Util.decode(certificateData);
		CertificateFactory cff = CertificateFactory.getInstance("X.509");
		ByteArrayInputStream bais = new ByteArrayInputStream(keyBytes);
		java.security.cert.Certificate cf = cff.generateCertificate(bais);
		// System.err.println("转换成String后的证书信息："+cf.toString());
		PublicKey pk1 = cf.getPublicKey(); // 得到证书文件携带的公钥
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm()); // 定义算法：RSA

		cipher.init(Cipher.ENCRYPT_MODE, pk1);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	public static String encryptByCertificate(String content, String publicKey) throws Exception {
		return Base64Util.encode(encryptByCertificate(content.getBytes(), publicKey));
	}

	/**
	 * <p>
	 * 公钥加密
	 * </p>
	 *
	 * @param data
	 *            源数据
	 * @param publicKey
	 *            公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
		byte[] keyBytes = Base64Util.decode(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	public static String encryptByPublicKey(String content, String publicKey) throws Exception {
		return Base64Util.encode(encryptByPublicKey(content.getBytes(), publicKey));
	}

	/****************************** pkcs8_rsa_private_key.pem public_key.pem去头尾 ********************************************/
	// /**
	// * 从文件中输入流中加载公钥
	// * @param in 公钥输入流
	// * @throws Exception 加载公钥时产生的异常
	// */
	// public static RSAPublicKey loadPublicKey(InputStream in) throws
	// Exception{
	// try {
	// BufferedReader br= new BufferedReader(new InputStreamReader(in));
	// String readLine= null;
	// StringBuilder sb= new StringBuilder();
	// while((readLine= br.readLine())!=null){
	// if(readLine.charAt(0)=='-'){
	// continue;
	// }else{
	// sb.append(readLine);
	// sb.append('\r');
	// }
	// }
	// return loadPublicKey(sb.toString());
	// } catch (IOException e) {
	// throw new Exception("公钥数据流读取错误");
	// } catch (NullPointerException e) {
	// throw new Exception("公钥输入流为空");
	// }
	// }
	//
	//
	// /**
	// * 从字符串中加载公钥
	// * @param publicKey 公钥数据字符串
	// * @throws Exception 加载公钥时产生的异常
	// */
	// public static RSAPublicKey loadPublicKey(String publicKey) throws
	// Exception{
	// try {
	// BASE64Decoder base64Decoder= new BASE64Decoder();
	// byte[] buffer= base64Decoder.decodeBuffer(publicKey);
	// KeyFactory keyFactory= KeyFactory.getInstance("RSA");
	// X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);
	// return (RSAPublicKey) keyFactory.generatePublic(keySpec);
	// } catch (NoSuchAlgorithmException e) {
	// throw new Exception("无此算法");
	// } catch (InvalidKeySpecException e) {
	// throw new Exception("公钥非法");
	// } catch (IOException e) {
	// throw new Exception("公钥数据内容读取错误");
	// } catch (NullPointerException e) {
	// throw new Exception("公钥数据为空");
	// }
	// }
	//
	// /**
	// * 从文件中加载私钥
	// * @param
	// * @return 是否成功
	// * @throws Exception
	// */
	// public static RSAPrivateKey loadPrivateKey(InputStream in) throws
	// Exception{
	// try {
	// BufferedReader br= new BufferedReader(new InputStreamReader(in));
	// String readLine= null;
	// StringBuilder sb= new StringBuilder();
	// while((readLine= br.readLine())!=null){
	// if(readLine.charAt(0)=='-'){
	// continue;
	// }else{
	// sb.append(readLine);
	// sb.append('\r');
	// }
	// }
	// return loadPrivateKey(sb.toString());
	// } catch (IOException e) {
	// throw new Exception("私钥数据读取错误");
	// } catch (NullPointerException e) {
	// throw new Exception("私钥输入流为空");
	// }
	// }
	//
	// public static RSAPrivateKey loadPrivateKey(String privateKey) throws
	// Exception{
	// try {
	// BASE64Decoder base64Decoder= new BASE64Decoder();
	// byte[] buffer= base64Decoder.decodeBuffer(privateKey);
	// PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(buffer);
	// KeyFactory keyFactory= KeyFactory.getInstance("RSA");
	// return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
	// } catch (NoSuchAlgorithmException e) {
	// throw new Exception("无此算法");
	// } catch (InvalidKeySpecException e) {
	// throw new Exception("私钥非法");
	// } catch (IOException e) {
	// throw new Exception("私钥数据内容读取错误");
	// } catch (NullPointerException e) {
	// throw new Exception("私钥数据为空");
	// }
	// }

}
