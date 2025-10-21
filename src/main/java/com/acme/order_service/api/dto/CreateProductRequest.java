package com.acme.order_service.api.dto;

public class CreateProductRequest {
    public String sku;
    public String name;
    public Double price;
    public Double vatRate;
    public Integer stockQty;
    public Boolean active;
}
