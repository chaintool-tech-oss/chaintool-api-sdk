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
public class OcrRecognitionRequest {

    /**
     * business tracing id
     */
    private String bizId;

    /**
     * doc type
     */
    private String docType;

    /**
     * image of ID front page
     * <P>
     *     base64
     * </P>
     */
    private String frontPageImage;

    /**
     * image of ID back page
     * <p>
     *     base64
     * </p>
     */
    private String backPageImage;


    Map<String, String> extInfo;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("bizId=").append(bizId);
        sb.append(",docType=").append(docType);
        sb.append(",frontPageImageLength=").append(frontPageImage == null ? 0 : frontPageImage.length());
        sb.append(",backPageImageLength=").append(backPageImage == null ? 0 : backPageImage.length());
        return sb.toString();
    }
}
