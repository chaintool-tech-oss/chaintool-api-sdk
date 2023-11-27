package com.chaintool.api.sdk.model.info;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @Author wesleyzen
 * @Date 11/27/23
 **/
@Data
public class FaceInfo {

    /**
     * face scan photo
     */
    private String faceRecognitionPhotoUrl;

    /**
     * stand for face scan status
     */
    private String faceRecognitionStatus;

    /**
     * stand for face expire time
     */
    private Date faceExpireTime;

    /**
     * compareResult
     */
    private Boolean compareResult;

    /**
     * compareStatus
     */
    private String compareStatus;

    /**
     * compareResultExpireTime
     */
    private Date compareResultExpireTime;

    /**
     * faceRecognitionRecheckUrl
     */
    private String faceRecognitionRecheckUrl;

    /**
     * faceCompareRecheckUrl
     */
    private String faceCompareRecheckUrl;

    /**
     * extInfo
     */
    private Map<String, String> extInfo;
}
