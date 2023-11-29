package com.chaintool.api.sdk.model;

import lombok.Data;

import java.util.Map;

/**
 * @Author wesleyzen
 * @Date 11/29/23
 **/
@Data
public class OcrRecheckResponse extends OpenApiCommonResult {
    private String compareResult;

    private Map<String, String> ocrResult;

    private String kycId;

    private String frontPageImage;

    private String backPageImage;
}
