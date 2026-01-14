package dev.sojunx.ecommerce.api.controller;

import dev.sojunx.ecommerce.api.application.dto.command.AddToCartCommand;
import dev.sojunx.ecommerce.api.application.dto.core.ApiResponse;
import dev.sojunx.ecommerce.api.application.service.cart.CartItemService;
import dev.sojunx.ecommerce.api.domain.entities.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart/items")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService service;

    @PostMapping
    ResponseEntity<?> addToCart(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody AddToCartCommand command) {
        service.addToCart(userDetails.user(), command);

        var res = ApiResponse.success("Added item to cart successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    ResponseEntity<?> removeFromCart(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable UUID id) {
        service.removeFromCart(userDetails.user(), id);

        var res = ApiResponse.success("Removed item from cart successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
