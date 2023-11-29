package com.chaintool.api.sdk.model;

import lombok.Data;

import java.util.Base64;

/**
 * @Author wesleyzen
 * @Date 11/29/23
 **/
@Data
public class RecheckInitResponse extends OpenApiCommonResult {

    private String recheckURI;

    private String clientCfg;
}
