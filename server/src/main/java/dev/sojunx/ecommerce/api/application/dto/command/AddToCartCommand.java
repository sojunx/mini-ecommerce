package dev.sojunx.ecommerce.api.application.dto.command;

import lombok.Data;

import java.util.UUID;

@Data
public class AddToCartCommand {
    private UUID productId;
    private int quantity;
}
