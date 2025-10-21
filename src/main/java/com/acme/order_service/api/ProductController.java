package com.acme.order_service.api;

import com.acme.order_service.api.dto.CreateProductRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @PostMapping
    public Object create(@RequestBody CreateProductRequest req) {
        // TODO: déléguer au service
        return java.util.Map.of("id", 1);
    }
}
