package dev.sojunx.ecommerce.api.application.service;

import dev.sojunx.ecommerce.api.application.dto.query.ProductDetails;
import dev.sojunx.ecommerce.api.application.mapper.ProductMapper;
import dev.sojunx.ecommerce.api.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repo;
    private final ProductMapper mapper;

    public List<ProductDetails> findAll() {
        var products = repo.findAll();

        return products.stream().map(mapper::toDto).toList();
    }

    public ProductDetails findById(UUID id) {
        var result = repo.findById(id);
        if (result.isEmpty()) throw new RuntimeException("Product not found");

        return mapper.toDto(result.get());
    }
}
