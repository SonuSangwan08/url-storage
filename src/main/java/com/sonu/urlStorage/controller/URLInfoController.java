package com.sonu.urlStorage.controller;

import com.sonu.urlStorage.exception.URLInfoNotFoundException;
import com.sonu.urlStorage.model.URLInfoRequest;
import com.sonu.urlStorage.model.URLInfoResponse;
import com.sonu.urlStorage.service.Impl.URLInfoServiceImpl;
import com.sonu.urlStorage.service.URLInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/url-info")
public class URLInfoController {
    @Autowired
    private URLInfoService urlInfoService;

    @PostMapping

    public ResponseEntity<URLInfoResponse> storeUrlInfo(@RequestBody URLInfoRequest urlInfoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(urlInfoService.saveUrl(urlInfoRequest));

    }

    @GetMapping
    public ResponseEntity<URLInfoResponse> getUrlInfo(@RequestParam String url) throws InterruptedException {
        URLInfoResponse urlInfoResponse = urlInfoService.fetchUrlInfo(url);
        return ResponseEntity.status(HttpStatus.OK).body(urlInfoResponse);

    }

    @GetMapping("/count")
    public ResponseEntity<URLInfoResponse> getUrlCountInfo(@RequestParam String url) {
        URLInfoResponse urlInfoResponse = urlInfoService.fetchUrlInfoCount(url);
        return ResponseEntity.status(HttpStatus.OK).body(urlInfoResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<List<URLInfoResponse>> getUrlList(@RequestParam Integer page, @RequestParam Integer size) {
        List<URLInfoResponse> urlInfoResponseList = urlInfoService.fetchUrlList(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(urlInfoService.fetchUrlList(page, size));

    }

}
