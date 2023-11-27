package com.chaintool.api.sdk.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ApiData {

    /**
     * the header of http data
     */
    private Map<String, List<String>> header;

    /**
     * the body of http data
     */
    private String content;
}