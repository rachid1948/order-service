package com.acme.order_service.api;

public record ErrorResponse(
        String timestamp,
        String code,
        String message,
        String field,
        String path
) {}
