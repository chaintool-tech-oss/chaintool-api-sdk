package com.chaintool.api.sdk.model.info;

import lombok.Data;

import java.util.Map;

/**
 * @Author wesleyzen
 * @Date 11/27/23
 **/
@Data
public class DocumentInfo {
    /**
     * the document type
     */
    private String documentType;

    /**
     * the document front page image
     */
    private String documentFrontPhotoUrl;

    /**
     * the document back page image
     *  if your upload this photo,that will be return
     */
    private String documentBackPhotoUrl;

    /**
     * the document id number
     */
    private String idNumber;

    /**
     * gender
     * only return :
     * F->Female
     * M->Male
     * X->unknown
     */
    private String gender;

    /**
     * country
     */
    private String country;

    /**
     * lastName
     */
    private String lastName;

    /**
     *  if has midName that will be return
     *  midName
     */
    private String midName;

    /**
     * firstName
     */
    private String firstName;

    /**
     * birthday
     */
    private String dateOfBirth;

    /**
     * status
     */
    private String status;

    /**
     *  valid
     */
    private boolean isValid;

    /**
     * expireTime
     */
    private String expireTime;

    /**
     * if valid is false this field is not null
     */
    private String recheckUrl;

    /**
     *
     */
    private Map<String, String> extInfo;
}
