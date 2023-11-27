package com.chaintool.api.sdk.model;

import lombok.Data;

import java.util.Map;

/**
 * @Author wesleyzen
 * @Date 11/27/23
 **/
@Data
public class KycUserQueryRequest {

    private String kycId;

    private String userAssociationId;

    Map<String, String> extInfo;

}
