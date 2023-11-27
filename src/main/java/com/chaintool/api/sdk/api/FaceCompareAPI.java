package com.chaintool.api.sdk.api;

import com.alibaba.fastjson.JSON;
import com.chaintool.api.sdk.client.OpenApiClient;
import com.chaintool.api.sdk.model.FaceCompareRequest;
import com.chaintool.api.sdk.model.FaceCompareResponse;

/**
 * @Author wesleyzen
 * @Date 11/27/23
 **/
public class FaceCompareAPI {
    private static final String API_NAME = "kyc.v1.facecompare";

    private OpenApiClient openApiClient;

    public FaceCompareAPI(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    /**
     * the faceCompare method of face compare
     * @param request the face compare request
     * @see FaceCompareRequest
     * @return the face compare response
     * @see FaceCompareResponse
     */
    public FaceCompareResponse faceCompare(FaceCompareRequest request) {
        String response = openApiClient.callOpenApi(API_NAME, JSON.toJSONString(request));
        return JSON.parseObject(response, FaceCompareResponse.class);
    }
}
