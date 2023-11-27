package com.chaintool.api.sdk.model;

import com.chaintool.api.sdk.model.info.DocumentInfo;
import com.chaintool.api.sdk.model.info.FaceInfo;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @Author wesleyzen
 * @Date 11/27/23
 **/
@Data
public class KycUserQueryResponse extends OpenApiCommonResult {

    private String kycId;

    private Date expireTime;

    private String kycStatus;

    private DocumentInfo document;

    private FaceInfo face;

    private Map<String, String> extInfo;
}
