package dev.sojunx.ecommerce.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String fullName;
    private String email;
    private String address;
    private String phoneNumber;

    private List<OrderItemRequest> items;
}
