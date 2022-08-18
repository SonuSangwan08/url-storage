package com.sonu.urlStorage.service.Impl;

import com.sonu.urlStorage.dao.URLInfoDao;
import com.sonu.urlStorage.entity.URLInfo;
import com.sonu.urlStorage.exception.URLInfoNotFoundException;
import com.sonu.urlStorage.model.URLInfoRequest;
import com.sonu.urlStorage.model.URLInfoResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@ExtendWith(MockitoExtension.class)
class URLInfoServiceImplTest {
    @Mock
    private URLInfoDao urlInfoDao;

    @InjectMocks
    private URLInfoServiceImpl urlInfoService;


    @Test
    void saveUrlTest() {
        URLInfoResponse urlInfoResponse = urlInfoService.saveUrl(new URLInfoRequest("www.google.com"));
        Assertions.assertTrue(null != urlInfoResponse);
        Assertions.assertTrue(null != urlInfoResponse.getShortKey());
        Assertions.assertEquals(0L, urlInfoResponse.getCount());

    }

    @Test
    void fetchUrlInfoTest() {
        Mockito.when(urlInfoDao.find(Mockito.anyString())).thenReturn(new URLInfo(1000000000, "7Ctw", new AtomicLong(1), null));
        URLInfoResponse urlInfoResponse = urlInfoService.fetchUrlInfo("www.google.com");
        Assertions.assertTrue(null != urlInfoResponse);
        Assertions.assertTrue(null != urlInfoResponse.getShortKey());
    }
    @Test
    void fetchUrlInfoNotFoundTest() {
        Mockito.when(urlInfoDao.find(Mockito.anyString())).thenReturn(null);
        Assertions.assertThrows(URLInfoNotFoundException.class, () -> urlInfoService.fetchUrlInfo("www.google.com"));
    }

    @Test
    void fetchUrlInfoCountTest() {
        Mockito.when(urlInfoDao.find(Mockito.anyString())).thenReturn(new URLInfo(1000000000, "7Ctw", new AtomicLong(10), null));
        URLInfoResponse urlInfoResponse = urlInfoService.fetchUrlInfoCount("www.google.com");
        Assertions.assertNotNull(urlInfoResponse);
        Assertions.assertEquals(10, urlInfoResponse.getCount());
    }

    @Test
    void fetchUrlInfoCountNotFoundTest() {
        Mockito.when(urlInfoDao.find(Mockito.anyString())).thenReturn(null);
        Assertions.assertThrows(URLInfoNotFoundException.class, () -> urlInfoService.fetchUrlInfoCount("www.google.com"));

    }

    @Test
    void fetchUrlListTest() {
        List<URLInfo> urlInfoList = new ArrayList<>();
        urlInfoList.add(new URLInfo(1000000000, "7Ctw", new AtomicLong(10), null));
        urlInfoList.add(new URLInfo(1000000001, "7Ctx", new AtomicLong(10), null));
        Mockito.when(urlInfoDao.findUrlList(Mockito.anyInt(), Mockito.anyInt())).thenReturn(urlInfoList);

        List<URLInfoResponse> urlInfoResponses = urlInfoService.fetchUrlList(1, 2);
        Assertions.assertTrue(null != urlInfoResponses);
        Assertions.assertEquals(2, urlInfoResponses.size());
    }

    @Test
    void fetchEmptyUrlListTest() {
        List<URLInfo> urlInfoList = new ArrayList<>();
        Mockito.when(urlInfoDao.findUrlList(Mockito.anyInt(), Mockito.anyInt())).thenReturn(urlInfoList);
        Assertions.assertThrows(URLInfoNotFoundException.class, () -> urlInfoService.fetchUrlList(1, 2));


    }
}