package dev.sojunx.ecommerce.api.application.dto.command;

import dev.sojunx.ecommerce.api.domain.enums.PaymentMethod;
import lombok.Data;

@Data
public class PlaceOrderCommand {
    private PaymentMethod paymentMethod;
}
