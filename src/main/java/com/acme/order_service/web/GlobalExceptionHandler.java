package com.acme.order_service.web;

import jakarta.validation.ConstraintViolationException;     // ✅ (et PAS javax)


import com.acme.order_service.api.ErrorCode;
import com.acme.order_service.api.ErrorResponse;
import com.acme.order_service.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private String nowIso() {
        return OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    private ResponseEntity<ErrorResponse> build(HttpStatus status, ErrorCode code, String message, String field, String path) {
        return ResponseEntity.status(status).body(
                new ErrorResponse(nowIso(), code.name(), message, field, path)
        );
    }

    // 400 - erreurs de validation sur @Valid DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        var fieldError = ex.getBindingResult().getFieldError();
        String field = fieldError != null ? fieldError.getField() : null;
        String message = fieldError != null ? fieldError.getDefaultMessage() : "Validation error";
        // path sera enrichi plus tard via un Filter; pour l’instant null
        return build(HttpStatus.BAD_REQUEST, ErrorCode.VALIDATION_ERROR, message, field, null);
    }

    // 404 - générique (on affinera quand on aura les services)
    @ExceptionHandler(java.util.NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NoSuchElementException ex) {
        return build(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND, ex.getMessage(), null, null);
    }

    // 409 - conflit métier (ex: SKU déjà existant)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex) {
        return build(HttpStatus.CONFLICT, ErrorCode.SKU_EXISTS, ex.getMessage(), null, null);
    }

    // (option) 422 - si on distingue d’autres règles métiers
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraint(ConstraintViolationException ex) {
        return build(HttpStatus.UNPROCESSABLE_ENTITY, ErrorCode.BUSINESS_RULE, "Business rule violation", null, null);
    }

}
