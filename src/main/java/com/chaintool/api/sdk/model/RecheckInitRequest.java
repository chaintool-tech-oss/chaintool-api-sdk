package com.chaintool.api.sdk.model;

import com.chaintool.api.sdk.model.enums.KYCPipeline;
import lombok.Data;

/**
 * @Author wesleyzen
 * @Date 11/29/23
 **/
@Data
public class RecheckInitRequest {

    private String kycId;

    private KYCPipeline pipeline;
}
