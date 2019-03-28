/*
 * Copyright 2015 mopote.com
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

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

/**
 * 
 * @since 1.0
 * @author Allen
 */
public class CommonAESUtils {

    private static final byte[] INIT_VECTOR = { 0x31, 0x37, 0x36, 0x35, 0x34, 0x33, 0x32, 0x31, 0x38, 0x27, 0x36, 0x35,
            0x33, 0x23, 0x32, 0x33 };

    public static String encrypt(String input, String key) throws Exception {
        byte[] crypted = null;
        javax.crypto.KeyGenerator kgen = javax.crypto.KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(key.getBytes()));
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec skey = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skey, new IvParameterSpec(INIT_VECTOR, 0, INIT_VECTOR.length));
        crypted = cipher.doFinal(input.getBytes());
        return new String(Hex.encode(crypted));
    }

    public static String decrypt(String input, String key) throws Exception {
        byte[] output = null;
        javax.crypto.KeyGenerator kgen = javax.crypto.KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(key.getBytes()));
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec skey = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skey, new IvParameterSpec(INIT_VECTOR, 0, INIT_VECTOR.length));
        output = cipher.doFinal(Hex.decode(input));
        return new String(output);
    }

    public static String encryptWith16BitsKey(String input, String key) throws Exception {
        byte[] crypted = null;
        SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skey);
        crypted = cipher.doFinal(input.getBytes());
        return new String(Base64.encode(crypted));
    }

    public static String decryptWith16BitsKey(String input, String key) throws Exception {
        byte[] output = null;
        SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skey);
        output = cipher.doFinal(Base64.decode(input));
        return new String(output);
    }
}