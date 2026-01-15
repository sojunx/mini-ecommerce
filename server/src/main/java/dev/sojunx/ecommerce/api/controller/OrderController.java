package dev.sojunx.ecommerce.api.controller;

import dev.sojunx.ecommerce.api.application.dto.command.PlaceOrderCommand;
import dev.sojunx.ecommerce.api.application.dto.core.ApiResponse;
import dev.sojunx.ecommerce.api.application.service.order.OrderService;
import dev.sojunx.ecommerce.api.domain.entities.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @PostMapping
    ResponseEntity<?> placeOrder(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody PlaceOrderCommand command) {
        var order = service.placeOrder(userDetails.user(), command);

        var res = ApiResponse.success("Order placed successfully", Map.of("order", order));
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
