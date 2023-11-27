package com.chaintool.api.sdk.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 *
 */
public class GenSignUtil {

    /**
     * sign with RSA private key
     * @param privateKey the RSA private key
     * @param content to be signed
     * @return the signature
     * @throws Exception exception
     */
    public static String sign(String privateKey, String content) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedPrivateKey = Base64.getDecoder().decode(privateKey);
        PrivateKey priKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedPrivateKey));
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(priKey);
        signature.update(content.getBytes());
        byte[] signed = signature.sign();
        return Base64.getEncoder().encodeToString(signed);
    }

    /**
     * verify signature with public key
     * @param publicKey the RSA public key
     * @param content the request content
     * @param sign the signature to be verified
     * @return true or false for the verifying result
     * @throws Exception exception
     */
    public static boolean verify(String publicKey, String content, String sign) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedPublicKey = Base64.getDecoder().decode(publicKey);
        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(decodedPublicKey));
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(pubKey);
        signature.update(content.getBytes());
        return signature.verify(Base64.getDecoder().decode(sign));
    }

}
