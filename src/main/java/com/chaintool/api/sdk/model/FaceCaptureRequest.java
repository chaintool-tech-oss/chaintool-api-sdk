package com.chaintool.api.sdk.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author wesleyzen
 * @Date 11/26/23
 **/
@Data
public class FaceCaptureRequest implements Serializable {

    private String kycId;

    private String callbackPage;

    Map<String, String> extInfo ;
}
