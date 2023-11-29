package com.chaintool.api.sdk.api;

import com.alibaba.fastjson.JSON;
import com.chaintool.api.sdk.client.OpenApiClient;
import com.chaintool.api.sdk.model.OcrRecheckRequest;
import com.chaintool.api.sdk.model.OcrRecheckResponse;
import com.chaintool.api.sdk.model.RecheckInitRequest;
import com.chaintool.api.sdk.model.RecheckInitResponse;

/**
 * @Author wesleyzen
 * @Date 11/29/23
 **/
public class OcrRecheckAPI {
    private static final String API_NAME = "kyc.v1.ocr_recheck";

    private OpenApiClient openApiClient;

    public OcrRecheckAPI(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    /**
     * the ocrRecheck method of query kyc user
     * @param request the ocr recheck request
     * @see OcrRecheckRequest
     * @return the ocr recheck response
     * @see OcrRecheckResponse
     */
    public OcrRecheckResponse ocrRecheck(OcrRecheckRequest request) {
        String response = openApiClient.callOpenApi(API_NAME, JSON.toJSONString(request));
        return JSON.parseObject(response, OcrRecheckResponse.class);
    }
}
