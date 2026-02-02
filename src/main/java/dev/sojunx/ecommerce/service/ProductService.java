package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.domain.entity.Product;
import dev.sojunx.ecommerce.exception.NotFoundException;
import dev.sojunx.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Product getProductById(UUID id) {
        var result = repository.findById(id);
        if (result.isEmpty())
            throw new NotFoundException("Product not found");

        return result.get();
    }

    public List<Product> searchByName(String name) {
        return repository.searchByName(name);
    }
}
