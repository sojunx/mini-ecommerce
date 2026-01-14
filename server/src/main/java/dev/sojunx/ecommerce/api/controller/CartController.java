package dev.sojunx.ecommerce.api.controller;

import dev.sojunx.ecommerce.api.application.dto.core.ApiResponse;
import dev.sojunx.ecommerce.api.application.service.cart.CartService;
import dev.sojunx.ecommerce.api.domain.entities.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @GetMapping
    ResponseEntity<?> getCart(@AuthenticationPrincipal CustomUserDetails userDetails) {
        var cart = service.getCart(userDetails.user());

        var res = ApiResponse.success("Fetched cart successfully", cart);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
