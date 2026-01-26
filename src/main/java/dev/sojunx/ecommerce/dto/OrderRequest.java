package dev.sojunx.ecommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String email;
    private List<OrderItemRequest> items;
}
