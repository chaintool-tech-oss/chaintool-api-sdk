package com.chaintool.api.sdk.model;

import lombok.Data;

/**
 * @Author wesleyzen
 * @Date 11/29/23
 **/
@Data
public class OcrRecheckRequest {

    private String clientCfg;

    private String frontPageImage;

    private String backPageImage;
}
