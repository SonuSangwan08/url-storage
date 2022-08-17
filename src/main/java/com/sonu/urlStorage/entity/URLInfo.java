package com.sonu.urlStorage.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class URLInfo {
    private String uniqueShortKey;
    private BigDecimal count;

}
