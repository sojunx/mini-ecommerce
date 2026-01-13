package dev.sojunx.ecommerce.api.controller;

import dev.sojunx.ecommerce.api.application.dto.core.ApiResponse;
import dev.sojunx.ecommerce.api.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    ResponseEntity<?> findAll() {
        var products = service.findAll();

        var res = ApiResponse.success("Fetched products successfully", Map.of("products", products));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable UUID id) {
        var product = service.findById(id);

        var res = ApiResponse.success("Fetched product successfully", Map.of("product", product));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
