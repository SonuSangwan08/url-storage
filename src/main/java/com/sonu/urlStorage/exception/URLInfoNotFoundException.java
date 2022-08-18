package com.sonu.urlStorage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class URLInfoNotFoundException extends RuntimeException {
    public URLInfoNotFoundException(String message) {
        super(message);
    }
}
