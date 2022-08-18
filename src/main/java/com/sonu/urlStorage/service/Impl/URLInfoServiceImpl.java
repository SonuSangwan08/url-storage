package com.sonu.urlStorage.service.Impl;

import com.sonu.urlStorage.dao.URLInfoDao;
import com.sonu.urlStorage.entity.URLInfo;
import com.sonu.urlStorage.exception.URLInfoNotFoundException;
import com.sonu.urlStorage.model.URLInfoRequest;
import com.sonu.urlStorage.model.URLInfoResponse;
import com.sonu.urlStorage.service.URLInfoService;
import com.sonu.urlStorage.util.UniqueShortKeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class URLInfoServiceImpl implements URLInfoService {
    @Autowired
    private URLInfoDao urlInfoDao;
    private AtomicLong sequenceNumber = new AtomicLong(10000000);

    @Override
    public URLInfoResponse saveUrl(URLInfoRequest urlInfoRequest) {
        URLInfo urlInfo = new URLInfo();
        urlInfo.setId(sequenceNumber.getAndIncrement());
        urlInfo.setUniqueShortKey(UniqueShortKeyGenerator.UniqueShortKey(urlInfo.getId()));
        urlInfo.setCount(new AtomicLong(0));
        urlInfoDao.save(urlInfoRequest.getUrl(), urlInfo);
        log.info("url saved successfully");
        URLInfoResponse urlInfoResponse = new URLInfoResponse();
        urlInfoResponse.setShortKey(urlInfo.getUniqueShortKey());
        urlInfoResponse.setCount(urlInfo.getCount().longValue());
        urlInfoResponse.setUrl(urlInfoRequest.getUrl());
        return urlInfoResponse;
    }

    @Override
    public URLInfoResponse fetchUrlInfo(String url) {
        URLInfo urlInfo = urlInfoDao.find(url);
        if (urlInfo == null) {
            log.error("url info not fount");
            throw new URLInfoNotFoundException("url info not found " + url);
        }
        URLInfoResponse urlInfoResponse = new URLInfoResponse();
        urlInfoResponse.setShortKey(urlInfo.getUniqueShortKey());
        urlInfo.getCount().getAndIncrement();
        return urlInfoResponse;
    }

    @Override
    public URLInfoResponse fetchUrlInfoCount(String url) {
        URLInfo urlInfo = urlInfoDao.find(url);
        if (urlInfo == null) {
            log.error("url info not fount");
            throw new URLInfoNotFoundException("url info not found " + url);
        }
        URLInfoResponse urlInfoResponse = new URLInfoResponse();
        urlInfoResponse.setCount(urlInfo.getCount().longValue());
        return urlInfoResponse;
    }

    @Override
    public List<URLInfoResponse> fetchUrlList(Integer page, Integer size) {
        List<URLInfo> list = urlInfoDao.findUrlList(page, size);
        if (list.isEmpty()) {
            log.error("url info not fount");
            throw new URLInfoNotFoundException("url info not found ");
        }
        List<URLInfoResponse> urlInfoResponseList = new ArrayList<>();
        for (URLInfo urlInfo : list) {
            URLInfoResponse urlInfoResponse = new URLInfoResponse();
            urlInfoResponse.setCount(urlInfo.getCount().longValue());
            urlInfoResponse.setUrl(urlInfo.getUrl());
            urlInfoResponseList.add(urlInfoResponse);
        }
        return urlInfoResponseList;
    }
}
