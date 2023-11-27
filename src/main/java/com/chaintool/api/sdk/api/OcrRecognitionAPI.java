package com.chaintool.api.sdk.api;

import com.alibaba.fastjson.JSON;
import com.chaintool.api.sdk.client.OpenApiClient;
import com.chaintool.api.sdk.model.OcrRecognitionRequest;
import com.chaintool.api.sdk.model.OcrRecognitionResponse;

/**
 * @Author wesleyzen
 * @Date 11/25/23
 **/
public class OcrRecognitionAPI {

    private static final String API_NAME = "kyc.v1.ocr";

    private OpenApiClient openApiClient;

    public OcrRecognitionAPI(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    /**
     * the recognize method of ID recognition API
     * @param request the doc recognition request
     * @see OcrRecognitionRequest
     * @return the doc recognition response
     * @see OcrRecognitionResponse
     */
    public OcrRecognitionResponse recognition(OcrRecognitionRequest request) {
        String response = openApiClient.callOpenApi(API_NAME, JSON.toJSONString(request));
        return JSON.parseObject(response, OcrRecognitionResponse.class);
    }
}
