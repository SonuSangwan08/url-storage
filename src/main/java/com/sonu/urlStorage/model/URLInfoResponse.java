package com.sonu.urlStorage.model;

import lombok.Data;

@Data
public class URLInfoResponse {
    private String url;
    private String shortKey;
    private long count;
}
