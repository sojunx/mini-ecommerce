package dev.sojunx.ecommerce.api.dto.command;

import lombok.Data;

@Data
public class AddToCartCommand {
    private String sku;
    private int quantity;
}