package com.chaintool.api.sdk.model;

import lombok.Data;
import java.util.Map;

/**
 * @Author wesleyzen
 * @Date 11/27/23
 **/
@Data
public class FaceCompareRequest {

    private String kycId;

    private String faceScanResponse;

    Map<String, String> extInfo;
}
