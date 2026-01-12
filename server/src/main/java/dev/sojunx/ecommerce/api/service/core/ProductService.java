package dev.sojunx.ecommerce.api.service.core;

import dev.sojunx.ecommerce.api.dto.query.ProductDetails;
import dev.sojunx.ecommerce.api.mapper.ProductMapper;
import dev.sojunx.ecommerce.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repo;
    private final ProductMapper mapper;

    public List<ProductDetails> findAll() {
        var products = repo.findAll();
        if (products.isEmpty()) return null;

        return products.stream().map(mapper::toDto).toList();
    }
}
