package com.chaintool.api.sdk.api;

import com.alibaba.fastjson.JSON;
import com.chaintool.api.sdk.client.OpenApiClient;
import com.chaintool.api.sdk.model.FaceCaptureRequest;
import com.chaintool.api.sdk.model.FaceCaptureResponse;

/**
 * @Author wesleyzen
 * @Date 11/26/23
 **/
public class FaceCaptureAPI {
    private static final String API_NAME = "kyc.v1.facecapture";

    private OpenApiClient openApiClient;

    public FaceCaptureAPI(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    /**
     * the faceCapture method of face scan init
     * @param request the face scan request
     * @see FaceCaptureRequest
     * @return the face scan response
     * @see FaceCaptureResponse
     */
    public FaceCaptureResponse faceCapture(FaceCaptureRequest request) {
        String response = openApiClient.callOpenApi(API_NAME, JSON.toJSONString(request));
        return JSON.parseObject(response, FaceCaptureResponse.class);
    }
}
