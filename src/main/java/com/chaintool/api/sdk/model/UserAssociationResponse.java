package com.chaintool.api.sdk.model;

import lombok.Data;

import java.util.Map;

/**
 * @Author wesleyzen
 * @Date 11/27/23
 **/
@Data
public class UserAssociationResponse extends OpenApiCommonResult {

    private String userAssociationId;

    private Map<String,String> extInfo;
}
