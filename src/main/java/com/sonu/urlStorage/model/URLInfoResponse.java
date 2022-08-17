package com.sonu.urlStorage.model;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class URLInfoResponse {
    private String url;
    private String shortKey;
    private BigDecimal count;
}
