package com.sonu.urlStorage.service;

import com.sonu.urlStorage.model.URLInfoRequest;
import com.sonu.urlStorage.model.URLInfoResponse;

import java.util.List;

public interface URLInfoService {
    URLInfoResponse saveUrl(URLInfoRequest urlInfoRequest);

    URLInfoResponse fetchUrlInfo(String url) throws InterruptedException;

    URLInfoResponse fetchUrlInfoCount(String url);

    List<URLInfoResponse> fetchUrlList(Integer page, Integer size);
}
