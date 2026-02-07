package dev.sojunx.ecommerce.controller;

import dev.sojunx.ecommerce.config.CustomUserDetails;
import dev.sojunx.ecommerce.dto.ApiResponse;
import dev.sojunx.ecommerce.dto.request.OrderRequest;
import dev.sojunx.ecommerce.mapper.OrderItemMapper;
import dev.sojunx.ecommerce.mapper.OrderMapper;
import dev.sojunx.ecommerce.service.OrderItemService;
import dev.sojunx.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    private final OrderItemService itemService;
    private final OrderMapper mapper;
    private final OrderItemMapper itemMapper;

    @GetMapping
    ResponseEntity<ApiResponse> getAllOrders(@AuthenticationPrincipal CustomUserDetails userDetails) {
        var orders = service.findAllByUserId(userDetails.user().getId());

        var res = ApiResponse.success("Success", orders.stream().map(mapper::toDto).toList());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse> getOrderById(@PathVariable UUID id) {
        var order = mapper.toDto(service.getOrderById(id));

        var res = ApiResponse.success("Success", order);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}/items")
    ResponseEntity<ApiResponse> getOrderItems(@PathVariable UUID id) {
        var items = itemService.getItemsByOrderId(id).stream().map(itemMapper::toDto).toList();

        var res = ApiResponse.success("Success", items);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<ApiResponse> createOrder(@RequestBody OrderRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        var order = service.createOrder(request, userDetails.user());

        var res = ApiResponse.success("Success", mapper.toDto(order));
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
