package com.sonu.urlStorage.service.Impl;

import com.sonu.urlStorage.dao.URLInfoDao;
import com.sonu.urlStorage.entity.URLInfo;
import com.sonu.urlStorage.model.URLInfoRequest;
import com.sonu.urlStorage.model.URLInfoResponse;
import com.sonu.urlStorage.service.URLInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class URLInfoServiceImpl implements URLInfoService {
    @Autowired
    private URLInfoDao urlInfoDao;

    @Override
    public URLInfoResponse saveUrl(URLInfoRequest urlInfoRequest) {
        URLInfo urlInfo = new URLInfo();
        urlInfo.setUniqueShortKey("abc");
        urlInfo.setCount(BigDecimal.ZERO);
        urlInfoDao.save(urlInfoRequest.getUrl(),urlInfo);
        URLInfoResponse urlInfoResponse=new URLInfoResponse();
        urlInfoResponse.setShortKey(urlInfo.getUniqueShortKey());
        urlInfoResponse.setCount(urlInfo.getCount());
        urlInfoResponse.setUrl(urlInfoRequest.getUrl());
        return urlInfoResponse;


    }

    @Override
    public URLInfoResponse fetchUrlInfo(String url) {
        URLInfo urlInfo=urlInfoDao.find(url);
        if(urlInfo!=null) {
            URLInfoResponse urlInfoResponse = new URLInfoResponse();
            urlInfoResponse.setShortKey(urlInfo.getUniqueShortKey());
            BigDecimal count = urlInfo.getCount();
            urlInfo.setCount(count.add(BigDecimal.ONE));
            return urlInfoResponse;
        }
        return null;
    }

    @Override
    public URLInfoResponse fetchUrlInfoCount(String url) {

            URLInfo urlInfo = urlInfoDao.find(url);
        if(urlInfo!=null) {
            URLInfoResponse urlInfoResponse = new URLInfoResponse();
            urlInfoResponse.setCount(urlInfo.getCount());
            return urlInfoResponse;
        }
        return null;

    }

    @Override
    public List<URLInfoResponse> fetchUrlList(Integer page, Integer size) {
        List<URLInfo> list= urlInfoDao.findUrlList(page,size);

        List<URLInfoResponse> urlInfoResponseList=new ArrayList<>();
        for(URLInfo urlInfo:list){
            URLInfoResponse urlInfoResponse = new URLInfoResponse();
            urlInfoResponse.setCount(urlInfo.getCount());
            urlInfoResponse.setShortKey(urlInfo.getUniqueShortKey());
            urlInfoResponseList.add(urlInfoResponse);
        }
        return urlInfoResponseList;
    }
}
