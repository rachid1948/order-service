package com.acme.order_service.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) { super(message); }
}
