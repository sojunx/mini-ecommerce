package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.domain.entity.Product;
import dev.sojunx.ecommerce.exception.NotFoundException;
import dev.sojunx.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    @Transactional(readOnly = true)
    public List<Product> getProducts() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProductById(UUID id) {
        var result = repository.findById(id);
        if (result.isEmpty())
            throw new NotFoundException("Product not found");

        return result.get();
    }
}
