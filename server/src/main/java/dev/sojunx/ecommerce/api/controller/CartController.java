package dev.sojunx.ecommerce.api.controller;

import dev.sojunx.ecommerce.api.domain.entities.user.User;
import dev.sojunx.ecommerce.api.dto.helper.ApiResponse;
import dev.sojunx.ecommerce.api.service.auth.CustomUserDetailsService;
import dev.sojunx.ecommerce.api.service.core.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;
    private final CustomUserDetailsService userDetailsService;

    @GetMapping
    ResponseEntity<?> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        var user = (User) userDetailsService.loadUserByUsername(userDetails.getUsername());
        var cart = service.findByUser(user);

        return new ResponseEntity<>(
                ApiResponse.success("Fetched cart successfully", cart),
                HttpStatus.OK
        );
    }

    record AddToCartCommand(String sku) {
    }

    @PostMapping("/")
    ResponseEntity<?> addToCart(@RequestBody AddToCartCommand command, @AuthenticationPrincipal UserDetails userDetails) {
        var user = (User) userDetailsService.loadUserByUsername(userDetails.getUsername());

        service.addProduct(user, command.sku());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
