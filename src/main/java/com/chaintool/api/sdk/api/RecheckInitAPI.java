package com.chaintool.api.sdk.api;

import com.alibaba.fastjson.JSON;
import com.chaintool.api.sdk.client.OpenApiClient;
import com.chaintool.api.sdk.model.KycUserQueryRequest;
import com.chaintool.api.sdk.model.KycUserQueryResponse;
import com.chaintool.api.sdk.model.RecheckInitRequest;
import com.chaintool.api.sdk.model.RecheckInitResponse;

/**
 * @Author wesleyzen
 * @Date 11/29/23
 **/
public class RecheckInitAPI {

    private static final String API_NAME = "kyc.v1.recheck_init";

    private OpenApiClient openApiClient;

    public RecheckInitAPI(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    /**
     * the recheckInit method of query kyc user
     * @param request the recheck init request
     * @see RecheckInitRequest
     * @return the recheck init response
     * @see RecheckInitResponse
     */
    public RecheckInitResponse recheckInit(RecheckInitRequest request) {
        String response = openApiClient.callOpenApi(API_NAME, JSON.toJSONString(request));
        return JSON.parseObject(response, RecheckInitResponse.class);
    }
}
