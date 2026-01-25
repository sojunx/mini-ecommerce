package dev.sojunx.ecommerce.dto.response;

import dev.sojunx.ecommerce.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OrderDto {
    private UUID id;
    private String fullName;
    private String email;
    private String address;
    private String phoneNumber;
    private Double total;
    private OrderStatus status;
}
