package dev.sojunx.ecommerce.api.service.core;

import dev.sojunx.ecommerce.api.domain.entities.product.Product;
import dev.sojunx.ecommerce.api.dto.query.ProductDetails;
import dev.sojunx.ecommerce.api.dto.query.ProductVariantDetails;
import dev.sojunx.ecommerce.api.mapper.ProductMapper;
import dev.sojunx.ecommerce.api.repository.ProductRepository;
import dev.sojunx.ecommerce.api.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repo;
    private final ProductVariantRepository variantRepo;
    private final ProductMapper mapper;

    public List<ProductDetails> findAll() {
        var products = repo.findAll();
        if (products.isEmpty()) return null;

        return products.stream().map(mapper::toDto).toList();
    }

    public ProductDetails findById(UUID id) {
        var result = repo.findById(id);
        if (result.isEmpty()) throw new RuntimeException("Product not found");

        var product = result.get();
        var productDetails = mapper.toDto(product);

        productDetails.setVariants(product.getVariants().stream().map(mapper::toDto).toList());
        return productDetails;
    }
}
