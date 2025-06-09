package com.example.flightapi.utils;

// 自定义异常类
public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String message) {
        super(message);
    }
}
