package com.sonu.urlStorage.exception;

import lombok.Data;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String detail;

    public ExceptionResponse(Date timestamp, String message, String detail) {
        this.timestamp = timestamp;
        this.message = message;
        this.detail = detail;
    }
}
