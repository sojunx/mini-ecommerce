package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.domain.entity.OrderItem;
import dev.sojunx.ecommerce.dto.request.OrderItemRequest;
import dev.sojunx.ecommerce.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository repository;
    private final ProductService productService;

    public void saveItem(OrderItem item) {
        repository.save(item);
    }

    public void createOrderItem(OrderItemRequest request, UUID orderId) {
        var product = productService.getProductById(request.getProductId());

        var item = new OrderItem();
        item.setProductId(product.getId());
        item.setOrderId(orderId);
        item.setQuantity(request.getQuantity());
        item.setPrice(product.getPrice());

        repository.save(item);
    }

    public List<OrderItem> getItemsByOrderId(UUID id) {
        return repository.findAllByOrderId(id);
    }

    public OrderItem getItemByOrderIdAndProductId(UUID orderId, UUID productId) {
        var result = repository.findByOrderIdAndProductId(orderId, productId);
        if (result.isEmpty())
            throw new RuntimeException("Order item not found with order id: " + orderId + " and product id: " + productId);

        return result.get();
    }
}
