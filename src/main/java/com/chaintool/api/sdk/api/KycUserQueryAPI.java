package com.chaintool.api.sdk.api;

import com.alibaba.fastjson.JSON;
import com.chaintool.api.sdk.client.OpenApiClient;
import com.chaintool.api.sdk.model.KycUserQueryRequest;
import com.chaintool.api.sdk.model.KycUserQueryResponse;

/**
 * @Author wesleyzen
 * @Date 11/27/23
 **/
public class KycUserQueryAPI {
    private static final String API_NAME = "kyc.v1.querykyc";

    private OpenApiClient openApiClient;

    public KycUserQueryAPI(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    /**
     * the queryKycUser method of query kyc user
     * @param request the query kyc user request
     * @see KycUserQueryRequest
     * @return the query kyc user response
     * @see KycUserQueryResponse
     */
    public KycUserQueryResponse queryKycUser(KycUserQueryRequest request) {
        String response = openApiClient.callOpenApi(API_NAME, JSON.toJSONString(request));
        return JSON.parseObject(response, KycUserQueryResponse.class);
    }
}
