package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.dto.response.ProductDto;
import dev.sojunx.ecommerce.mapper.ProductMapper;
import dev.sojunx.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public List<ProductDto> getAllProducts() {
        var products = repository.findAll();

        return products.stream().map(mapper::toDto).toList();
    }

    public ProductDto getProductById(UUID id) {
        var result = repository.findById(id);
        if (result.isEmpty())
            throw new RuntimeException("Product not found with id: " + id);

        return mapper.toDto(result.get());
    }
}
