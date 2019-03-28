package com.zhuangjb.security;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;
import javax.crypto.Cipher;

public class RSAMain {
	public static String module = "5m9m14XH3oqLJ8bNGw9e4rGpXpcktv9MSkHSVFVMjHbfv+SJ5v0ubqQxa5YjLN4vc49z7SVju8s0X4gZ6AzZTn06jzWOgyPRV54Q4I0DCYadWW4Ze3e+BOtwgVU1Og3qHKn8vygoj40J6U85Z/PTJu3hN1m75Zr195ju7g9v4Hk=";
	public static String exponentString = "AQAB";
	public static String delement = "vmaYHEbPAgOJvaEXQl+t8DQKFT1fudEysTy31LTyXjGu6XiltXXHUuZaa2IPyHgBz0Nd7znwsW/S44iql0Fen1kzKioEL3svANui63O3o5xdDeExVM6zOf1wUUh/oldovPweChyoAdMtUzgvCbJk1sYDJf++Nr0FeNW1RB1XG30=";
	public static final String OxCAFEBABE = "DGC_FAFD6D01A21F50344D3A443FFE059D1D6BEEA3AA1C7E567603E26D8A";

	public static void main(String[] args) {
	}

	public static byte[] Dencrypt(byte[] encrypted) {
		try {
			byte[] expBytes = Base64.decode(delement);
			byte[] modBytes = Base64.decode(module);

			BigInteger modules = new BigInteger(1, modBytes);
			BigInteger exponent = new BigInteger(1, expBytes);

			KeyFactory factory = KeyFactory.getInstance("RSA");
			Cipher cipher = Cipher.getInstance("RSA/NONE/NoPadding");

			RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(modules, exponent);
			PrivateKey privKey = factory.generatePrivate(privSpec);
			cipher.init(2, privKey);
			byte[] decrypted = cipher.doFinal(encrypted);
			return decrypted;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}