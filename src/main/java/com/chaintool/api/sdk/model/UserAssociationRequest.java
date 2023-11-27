package com.chaintool.api.sdk.model;

import lombok.Data;

import java.util.Map;

/**
 * @Author wesleyzen
 * @Date 11/27/23
 **/
@Data
public class UserAssociationRequest {

    private String walletName;

    private String walletAddress;

    private String isBlockchain;

    private String blockChainType;

    private String blockChainAddress;

    private Map<String, String> extInfo;
}
