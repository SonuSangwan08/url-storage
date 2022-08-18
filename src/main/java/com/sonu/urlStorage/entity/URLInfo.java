package com.sonu.urlStorage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicLong;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class URLInfo {
    private long id;
    private String uniqueShortKey;
    private AtomicLong count;
    private String url;

}
