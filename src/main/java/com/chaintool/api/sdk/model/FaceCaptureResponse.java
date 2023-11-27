package com.chaintool.api.sdk.model;

import lombok.Data;
import java.util.Map;

/**
 * @Author wesleyzen
 * @Date 11/26/23
 **/
@Data
public class FaceCaptureResponse extends OpenApiCommonResult {

    private String faceScanUrl;

    Map<String, String> extInfo;
}
