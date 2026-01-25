package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.dto.response.OrderItemDto;
import dev.sojunx.ecommerce.mapper.OrderItemMapper;
import dev.sojunx.ecommerce.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository repository;
    private final OrderItemMapper mapper;

    public List<OrderItemDto> getAllItemsByOrderId(UUID id) {
        var items = repository.findAllByOrder_Id(id);

        return items.stream().map(mapper::toDto).toList();
    }
}
