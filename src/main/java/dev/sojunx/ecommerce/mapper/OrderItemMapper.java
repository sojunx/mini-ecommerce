package dev.sojunx.ecommerce.mapper;

import dev.sojunx.ecommerce.dto.request.OrderItemRequest;
import dev.sojunx.ecommerce.dto.response.OrderItemDto;
import dev.sojunx.ecommerce.entity.OrderItem;
import dev.sojunx.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemMapper {
    private final ProductRepository productRepository;

    public OrderItem toEntity(OrderItemRequest request) {
        var result = productRepository.findById(request.getProductId());
        if (result.isEmpty()) throw new RuntimeException("Product not found with id: " + request.getProductId());

        return OrderItem.builder()
                .name(result.get().getName())
                .product(result.get())
                .quantity(request.getQuantity())
                .price(result.get().getPrice())
                .total(result.get().getPrice() * request.getQuantity())
                .build();
    }

    public OrderItemDto toDto(OrderItem item) {
        var product = item.getProduct();

        return OrderItemDto.builder()
                .productId(product.getId())
                .productName(item.getName())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .total(item.getTotal())
                .build();
    }
}
