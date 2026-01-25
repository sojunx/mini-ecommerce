package dev.sojunx.ecommerce.controller;

import dev.sojunx.ecommerce.dto.ApiResponse;
import dev.sojunx.ecommerce.dto.request.OrderRequest;
import dev.sojunx.ecommerce.entity.User;
import dev.sojunx.ecommerce.service.OrderService;
import dev.sojunx.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    private final UserService userService;

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse> getOrderById(@PathVariable UUID id) {
        var order = service.getOrderById(id);

        var res = ApiResponse.success("Order fetched successfully", order);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<ApiResponse> createOrder(@RequestBody OrderRequest request, Authentication authentication) {
        User user = null;
        if (authentication != null)
            user = userService.findByEmail(authentication.getName());

        var order = service.createOrder(request, user);

        var res = ApiResponse.success("Order created", order);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
