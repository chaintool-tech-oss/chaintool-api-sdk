package com.chaintool.api.sdk.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @Description
 * @Author wesleyzen
 * @Date 2023/5/11 18:52
 **/
public class AESUtil {
    public AESUtil() {
    }

    /**
     * encrypt
     * @param key
     * @param content
     * @return
     * @throws Exception
     */
    public static String encrypt(byte[] key, String content) throws Exception {
        SecretKeySpec spec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, spec);
        byte[] byteEnc = cipher.doFinal(content.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(byteEnc);
    }

    /**
     * decrypt
     * @param key
     * @param content
     * @return
     * @throws Exception
     */
    public static String decrypt(byte[] key, String content) throws Exception {
        SecretKeySpec spec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, spec);
        byte[] encryptByte = Base64.getDecoder().decode(content);
        byte[] original = cipher.doFinal(encryptByte);
        return new String(original, "UTF-8");
    }

    /**
     * generate Key
     * @param length
     * @return
     * @throws Exception
     */
    public static byte[] generateKey(int length) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(length);
        SecretKey secretKey = keyGen.generateKey();
        byte[] key = secretKey.getEncoded();
        return key;
    }
}
