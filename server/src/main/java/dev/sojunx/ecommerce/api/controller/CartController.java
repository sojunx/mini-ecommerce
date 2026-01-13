package dev.sojunx.ecommerce.api.controller;

import dev.sojunx.ecommerce.api.application.dto.command.AddToCartCommand;
import dev.sojunx.ecommerce.api.application.dto.core.ApiResponse;
import dev.sojunx.ecommerce.api.application.service.CartService;
import dev.sojunx.ecommerce.api.domain.entities.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @GetMapping
    ResponseEntity<?> getCart(@AuthenticationPrincipal CustomUserDetails userDetails) {
        var cart = service.findByUser(userDetails.user());

        var res = ApiResponse.success("Fetched cart successfully", cart);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> addToCart(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody AddToCartCommand command) {
        service.addToCart(userDetails.user(), command);

        var res = ApiResponse.success("Added item to cart successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
