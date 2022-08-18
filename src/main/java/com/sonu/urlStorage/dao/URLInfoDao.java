package com.sonu.urlStorage.dao;

import com.sonu.urlStorage.entity.URLInfo;
import com.sonu.urlStorage.model.URLInfoRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class URLInfoDao {
    private Map<String, URLInfo> urlInfoMap = new ConcurrentHashMap<>();
    private List<String> urls = new ArrayList<>();

    public void save(String url, URLInfo urlInfo) {
        if (!urlInfoMap.containsKey(url)) {
            urls.add(url);
            urlInfoMap.put(url, urlInfo);
        }
    }

    public URLInfo find(String url) {
        if (urlInfoMap.containsKey(url)) {
            return urlInfoMap.get(url);
        }
        return null;
    }

    public List<URLInfo> findUrlList(Integer page, Integer size) {
       List<URLInfo> urlInfoList=new ArrayList<>();
        for(int i=((page-1)*size);i<Math.min((page*size),urls.size());i++) {
            URLInfo urlInfo = urlInfoMap.get(urls.get(i));
            urlInfo.setUrl(urls.get(i));
            System.out.println(urlInfo);
            urlInfoList.add(urlInfo);
        }
        return urlInfoList;

    }
}
