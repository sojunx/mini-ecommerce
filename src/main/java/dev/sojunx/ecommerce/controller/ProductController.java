package dev.sojunx.ecommerce.controller;

import dev.sojunx.ecommerce.dto.ApiResponse;
import dev.sojunx.ecommerce.mapper.ProductMapper;
import dev.sojunx.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper;

    @GetMapping
    ResponseEntity<ApiResponse> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        var products = service.findAll(pageable);

        var res = ApiResponse.success("Success", products.map(mapper::toDto));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse> getProductById(@PathVariable UUID id) {
        var product = mapper.toDto(service.getProductById(id));

        var res = ApiResponse.success("Success", product);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/search")
    ResponseEntity<ApiResponse> searchProduct(@RequestParam String name) {
        var products = service.searchByName(name);

        var res = ApiResponse.success("Success", products);
        return ResponseEntity.ok(res);
    }
}
