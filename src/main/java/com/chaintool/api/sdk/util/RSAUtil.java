package com.chaintool.api.sdk.util;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @Description
 * @Author wesleyzen
 * @Date 2023/5/31 23:07
 **/
public class RSAUtil {
    /**
     * encrypt with RSA public key
     *
     * @param publicKey the RSA public key
     * @param content   original content to be encrypted
     * @return the encrypted content in base64 format
     * @throws Exception exception
     */
    public static String encrypt(String publicKey, byte[] content) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return Base64.getEncoder().encodeToString(cipher.doFinal(content));
    }

    /**
     * decrypt with RSA private key
     *
     * @param privateKey the RSA private key
     * @param content    content to be decrypted in base64 format
     * @return original content
     * @throws Exception exception
     */
    public static byte[] decrypt(String privateKey, String content) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
        return cipher.doFinal(Base64.getDecoder().decode(content));
    }

    /**
     * construct public key
     *
     * @param base64PublicKey public key in base64 format
     * @return the public key
     * @throws Exception exception
     */
    private static PublicKey getPublicKey(String base64PublicKey) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * construct private key
     *
     * @param base64PrivateKey private key in base64 format
     * @return the private key
     * @throws Exception exception
     */
    private static PrivateKey getPrivateKey(String base64PrivateKey) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey));
        return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }
}
