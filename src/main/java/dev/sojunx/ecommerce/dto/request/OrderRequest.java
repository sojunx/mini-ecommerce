package dev.sojunx.ecommerce.dto.request;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderRequest {
    private UUID userId;
    private String email;
    private List<OrderItemRequest> items;
}
