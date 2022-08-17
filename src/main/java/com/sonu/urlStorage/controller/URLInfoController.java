package com.sonu.urlStorage.controller;

import com.sonu.urlStorage.model.URLInfoRequest;
import com.sonu.urlStorage.model.URLInfoResponse;
import com.sonu.urlStorage.service.Impl.URLInfoServiceImpl;
import com.sonu.urlStorage.service.URLInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/url-info")
public class URLInfoController {
    @Autowired
    private URLInfoService urlInfoService;
    @PostMapping
    public ResponseEntity<URLInfoResponse> storeUrlInfo(@RequestBody URLInfoRequest urlInfoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(urlInfoService.saveUrl(urlInfoRequest));

    }
    @GetMapping
    public ResponseEntity<URLInfoResponse> getUrlInfo(@RequestParam String url){
        URLInfoResponse urlInfoResponse =urlInfoService.fetchUrlInfo(url);
        if(urlInfoResponse!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(urlInfoResponse);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);


    }
    @GetMapping("/count")
    public ResponseEntity<URLInfoResponse> getUrlCountInfo(@RequestParam String url){
        URLInfoResponse urlInfoResponse =urlInfoService.fetchUrlInfoCount(url);
        if(urlInfoResponse!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(urlInfoResponse);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/list")
    public ResponseEntity<List<URLInfoResponse>> getUrlList(@RequestParam Integer page, @RequestParam Integer size){

        return ResponseEntity.status(HttpStatus.OK).body(urlInfoService.fetchUrlList(page,size));

    }

}
