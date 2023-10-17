package com.wsj.apiclientsdk.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiDataFieldRequest implements Serializable {
    private static final long serialVersionUID = -6486005224268968744L;

    private String path;
    private String method;
    private String paramsJson;
    private String headersJson;
}
