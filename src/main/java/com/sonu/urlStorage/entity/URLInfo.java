package com.sonu.urlStorage.entity;

import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

@Data
public class URLInfo {
    private long id;
    private String uniqueShortKey;
    private AtomicLong count;
    private String url;

}
