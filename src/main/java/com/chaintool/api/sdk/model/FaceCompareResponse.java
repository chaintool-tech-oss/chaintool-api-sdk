package com.chaintool.api.sdk.model;

import lombok.Data;

import java.util.Map;

/**
 * @Author wesleyzen
 * @Date 11/27/23
 **/
@Data
public class FaceCompareResponse extends OpenApiCommonResult {

    private boolean compareResult;

    Map<String, String> extInfo ;
}
