package dev.sojunx.ecommerce.controller;

import dev.sojunx.ecommerce.dto.ApiResponse;
import dev.sojunx.ecommerce.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService service;

    @GetMapping("/{id}/items")
    ResponseEntity<ApiResponse> getAllItemsByOrderId(@PathVariable UUID id) {
        var items = service.getAllItemsByOrderId(id);

        var res = ApiResponse.success("Order items fetched successfully", items);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
