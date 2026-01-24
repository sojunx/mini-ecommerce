package dev.sojunx.ecommerce.controller;

import dev.sojunx.ecommerce.dto.ApiResponse;
import dev.sojunx.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    ResponseEntity<ApiResponse> getAllProducts() {
        var products = service.getAllProducts();

        var res = ApiResponse.success("Products fetched successfully", products);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse> getProductById(@PathVariable UUID id) {
        var product = service.getProductById(id);

        var res = ApiResponse.success("Product fetched successfully", product);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
