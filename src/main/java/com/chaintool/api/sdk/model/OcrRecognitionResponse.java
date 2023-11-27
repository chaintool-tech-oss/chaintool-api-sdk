package com.chaintool.api.sdk.model;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wesleyzen
 * @Date 11/25/23
 **/
@Data
public class OcrRecognitionResponse extends OpenApiCommonResult {

    private String kycId;

    private Map<String, String> ocrResult;

    private String recognitionResult;

    Map<String, String> extInfo;
}
