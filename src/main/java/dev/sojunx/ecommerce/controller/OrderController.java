package dev.sojunx.ecommerce.controller;

import dev.sojunx.ecommerce.domain.enums.OrderStatus;
import dev.sojunx.ecommerce.dto.ApiResponse;
import dev.sojunx.ecommerce.dto.OrderRequest;
import dev.sojunx.ecommerce.mapper.OrderItemMapper;
import dev.sojunx.ecommerce.mapper.OrderMapper;
import dev.sojunx.ecommerce.service.OrderItemService;
import dev.sojunx.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {
    private final OrderService service;
    private final OrderItemService itemService;
    private final OrderMapper mapper;
    private final OrderItemMapper itemMapper;

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
    ResponseEntity<ApiResponse> createOrder(@RequestBody OrderRequest request) {
        var order = mapper.toDto(service.createOrder(request));

        var res = ApiResponse.success("Success", order);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    ResponseEntity<ApiResponse> completeOrder(@PathVariable UUID id) {
        service.updateOrderStatus(id, OrderStatus.COMPLETED);

        var res = ApiResponse.success("Success");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
