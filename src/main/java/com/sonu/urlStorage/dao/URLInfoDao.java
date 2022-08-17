package com.sonu.urlStorage.dao;

import com.sonu.urlStorage.entity.URLInfo;
import com.sonu.urlStorage.model.URLInfoRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class URLInfoDao {
    private Map<String, URLInfo> urlInfoMap = new ConcurrentHashMap<>();
    private List<String> urlInfoList = new ArrayList<>();


    public void save(String url, URLInfo urlInfo) {
        urlInfoMap.put(url,urlInfo);

    }


    public URLInfo find(String url) {
        if(urlInfoMap.containsKey(url)){
            return urlInfoMap.get(url);
        }
        return null;
    }

    public List<URLInfo> findUrlList(Integer page, Integer size) {
        return List.copyOf(urlInfoMap.values());

    }
}
