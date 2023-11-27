package com.chaintool.api.sdk.client;

import com.alibaba.fastjson.parser.ParserConfig;
import com.chaintool.api.sdk.model.ApiData;
import com.chaintool.api.sdk.util.AESUtil;
import com.chaintool.api.sdk.util.GenSignUtil;
import com.chaintool.api.sdk.util.RSAUtil;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wesleyzen
 * @Date 11/25/23
 **/
@Data
public class OpenApiClient {

    private static final Logger logger = LoggerFactory.getLogger(OpenApiClient.class);

    private String hostUrl;

    private String token;

    private String merchantPrivateKey;

    private String openApiPublicKey;

    private boolean signed;

    private boolean encrypted;


    /**
     * default constructor with signature and encryption
     */
    public OpenApiClient() {
        ParserConfig parserConfig = ParserConfig.getGlobalInstance();
        parserConfig.setSafeMode(true);

        this.signed = true;
        this.encrypted = true;
    }

    /**
     * invoke API gateway with the optional signature and encryption processes
     *
     * @param apiName the name of API
     * @param request the request content in json string format
     * @return the response content in json string format
     */
    public String callOpenApi(String apiName, String request) {

        String encryptKey = null;
        byte[] key = null;
        try {
            if (encrypted) {
                // Generate aes key
                key = AESUtil.generateKey(256);
                // encrypt content
                request = AESUtil.encrypt(key, request);
                // encrypt aes key
                encryptKey = RSAUtil.encrypt(openApiPublicKey, key);
            }
        } catch (Exception e) {
            logger.error("encrypt key fail.", e);
        }
        String resultContent = null;
        try {
            // 1. sign the signature
            String reqTime = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"));
            String signature = null;
            if (signed) {
                signature = sign(merchantPrivateKey, apiName, token, reqTime, request);
            }
            // 2. Send data and receive response
            String url = hostUrl +"/"+ apiName.replaceAll("\\.", "/");
            if (logger.isInfoEnabled()) {
                logger.info("API URL = " + url);
            }
            ApiData data = post(url, encryptKey, token, reqTime, signature, request);
            if (logger.isInfoEnabled()) {
                if (!data.getHeader().isEmpty()) {
                    logger.info("Response header = " + data.getHeader().toString());
                }
                if(StringUtils.isNotBlank(data.getContent())){
                    logger.info("Response encrypt content = " + data.getContent());
                }
            }

            // 3. Check Signature
            if (data.getHeader().get("Signature") != null) {
                Map<String, String> responseSign = splitEncryptOrSignature(data.getHeader().get("Signature").get(0));
                String toSignContent = buildResponseSignatureContent(apiName, token, data.getHeader().get("Response-Time").get(0),
                        data.getContent());
                boolean checkSignResult = GenSignUtil.verify(openApiPublicKey, toSignContent,
                        URLDecoder.decode(responseSign.get("signature"), "UTF-8"));
                if (logger.isInfoEnabled()) {
                    logger.info("check response signature " + checkSignResult);
                }
            }

            resultContent = data.getContent();
            // 4. decrypt
            if (encrypted) {
                if (data.getHeader().get("Encrypt") != null) {
                    Map<String, String> encrypt = splitEncryptOrSignature(data.getHeader().get("Encrypt").get(0));
                    if (encrypt != null && encrypt.get("symmetricKey") != null) {
                        byte[] decryptedAESKey = RSAUtil.decrypt(merchantPrivateKey,
                                URLDecoder.decode(encrypt.get("symmetricKey"), StandardCharsets.UTF_8.name()));
                        resultContent = AESUtil.decrypt(decryptedAESKey, resultContent);
                    }
                }
            }

            if(logger.isInfoEnabled()){
                logger.info("Response decrypt content ="  + resultContent);
            }
        } catch (Exception e) {
            logger.error("failed to get response.", e);
        }
        return resultContent;
    }

    private String sign(String merchantPrivateKey, String api, String token, String reqTime, String request) throws Exception {
        StringBuffer sb = new StringBuffer(request.length() + 256);
        sb.append("POST ").append("/").append(api.replaceAll("\\.", "/")).append("\n");
        sb.append(token).append(".").append(reqTime).append(".").append(request);
        String str = sb.toString();
        return GenSignUtil.sign(merchantPrivateKey, str);
    }

    private ApiData post(String baseUrl, String encryptKey, String token, String reqTime, String signature, String request) {
        ApiData data = new ApiData();
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        StringBuffer result = new StringBuffer(1024 * 20);

        try {
            URL realUrl = new URL(baseUrl);
            URLConnection conn = realUrl.openConnection();
            if (encryptKey != null) {
                conn.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
            } else {
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            }
            conn.setRequestProperty("TOKEN", token);
            conn.setRequestProperty("Request-Time", reqTime);
            if (signature != null) {
                conn.setRequestProperty("Signature",
                        "algorithm=RSA256, signature=" + URLEncoder.encode(signature, StandardCharsets.UTF_8.name()));
            }
            if (encryptKey != null) {
                conn.setRequestProperty("Encrypt",
                        "algorithm=RSA_AES, symmetricKey=" + URLEncoder.encode(encryptKey, StandardCharsets.UTF_8.name()));
            }
            if (logger.isInfoEnabled()) {
                logger.info("RequestProperties = " + conn.getRequestProperties().toString());
            }
            conn.setDoOutput(true);
            conn.setDoInput(true);
            outputStreamWriter = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8.name());
            outputStreamWriter.write(request);
            outputStreamWriter.flush();
            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8.name()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            data.setContent(result.toString());
            data.setHeader(conn.getHeaderFields());
        } catch (Exception e) {
            logger.error("failed to do request:{}.", request, e);
        } finally {
            try {
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                logger.error("close io fail.", ex);
            }
        }
        return data;
    }

    private Map<String, String> splitEncryptOrSignature(String value) {
        if (value == null) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        String[] pairs = value.split(",");
        if (pairs == null) {
            return map;
        }
        for (String pair : pairs) {
            if (pair == null) {
                continue;
            }
            String[] kv = pair.trim().split("=");
            if (kv != null && kv.length == 2 && kv[0] != null) {
                map.put(kv[0].trim(), kv[1].trim());
            }
        }
        return map;
    }

    private String buildResponseSignatureContent(String apiName, String token, String responseTime, String response) {
        StringBuffer sb = new StringBuffer(response.length() + 256);
        sb.append("POST ").append("/").append(apiName.replaceAll("\\.", "/")).append("\n");
        sb.append(token).append(".").append(responseTime).append(".").append(response);
        return sb.toString();
    }
}
