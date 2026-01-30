package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.domain.entity.OrderItem;
import dev.sojunx.ecommerce.dto.request.OrderItemRequest;
import dev.sojunx.ecommerce.exception.NotFoundException;
import dev.sojunx.ecommerce.repository.OrderItemRepository;
import dev.sojunx.ecommerce.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository repository;
    private final ProductService productService;
    private final ReviewRepository reviewRepository;

    public void saveItem(OrderItem item) {
        repository.save(item);
    }

    public void createOrderItem(OrderItemRequest request, UUID orderId, UUID userId) {
        var product = productService.getProductById(request.getProductId());

        var item = new OrderItem();
        item.setName(product.getName());
        item.setProductId(product.getId());
        item.setOrderId(orderId);
        item.setQuantity(request.getQuantity());
        item.setPrice(product.getPrice());

        // Check if user already reviewed this product
        // NOTES: Some issue happens in some cases
        boolean isReviewed = reviewRepository.existsByUserIdAndProductId(userId, product.getId());
        item.setReviewed(isReviewed);

        repository.save(item);
    }

    public List<OrderItem> getItemsByOrderId(UUID id) {
        return repository.findAllByOrderId(id);
    }

    public OrderItem getItemByOrderIdAndProductId(UUID orderId, UUID productId) {
        var result = repository.findByOrderIdAndProductId(orderId, productId);
        if (result.isEmpty())
            throw new NotFoundException("Order item not found");

        return result.get();
    }
}
