package com.chaintool.api.sdk.api;

import com.alibaba.fastjson.JSON;
import com.chaintool.api.sdk.client.OpenApiClient;
import com.chaintool.api.sdk.model.UserAssociationRequest;
import com.chaintool.api.sdk.model.UserAssociationResponse;

/**
 * @Author wesleyzen
 * @Date 11/27/23
 **/
public class UserAssociationAPI {

    private static final String API_NAME = "kyc.v1.userassociation";

    private OpenApiClient openApiClient;

    public UserAssociationAPI(OpenApiClient openApiClient) {
        this.openApiClient = openApiClient;
    }

    /**
     * the userAssociation method of user association
     * @param request the user association request
     * @see UserAssociationRequest
     * @return the user association response
     * @see UserAssociationResponse
     */
    public UserAssociationResponse userAssociation(UserAssociationRequest request) {
        String response = openApiClient.callOpenApi(API_NAME, JSON.toJSONString(request));
        return JSON.parseObject(response, UserAssociationResponse.class);
    }
}
