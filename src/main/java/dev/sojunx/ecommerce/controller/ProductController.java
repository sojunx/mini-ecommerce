package dev.sojunx.ecommerce.controller;

import dev.sojunx.ecommerce.dto.ApiResponse;
import dev.sojunx.ecommerce.mapper.ProductMapper;
import dev.sojunx.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper;

    @GetMapping
    ResponseEntity<ApiResponse> getProducts() {
        var products = service.getProducts().stream().map(mapper::toDto).toList();

        var res = ApiResponse.success("Success", products);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse> getProductById(@PathVariable UUID id) {
        var product = mapper.toDto(service.getProductById(id));

        var res = ApiResponse.success("Success", product);
        return ResponseEntity.ok(res);
    }
}
