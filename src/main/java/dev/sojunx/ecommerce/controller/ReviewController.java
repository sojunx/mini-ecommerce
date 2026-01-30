package dev.sojunx.ecommerce.controller;

import dev.sojunx.ecommerce.config.CustomUserDetails;
import dev.sojunx.ecommerce.dto.ApiResponse;
import dev.sojunx.ecommerce.dto.request.DeleteReviewRequest;
import dev.sojunx.ecommerce.dto.request.ReviewRequest;
import dev.sojunx.ecommerce.dto.request.UpdateReviewRequest;
import dev.sojunx.ecommerce.mapper.ReviewMapper;
import dev.sojunx.ecommerce.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService service;
    private final ReviewMapper mapper;

    @GetMapping("/{product_id}")
    ResponseEntity<ApiResponse> getReviews(@PathVariable UUID product_id) {
        var reviews = service.getReviews(product_id).stream().map(mapper::toDto).toList();

        var res = ApiResponse.success("Success", reviews);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{product_id}/stats")
    ResponseEntity<ApiResponse> getStats(@PathVariable UUID product_id) {
        var stats = service.getReviewStats(product_id);

        var res = ApiResponse.success("Success", stats);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    ResponseEntity<ApiResponse> createReview(@RequestBody ReviewRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        var review = service.createReview(request, userDetails.user());

        var res = ApiResponse.success("Success", mapper.toDto(review));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse> updateReview(@PathVariable UUID id, @RequestBody UpdateReviewRequest request) {
        var review = service.updateReview(id, request);

        var res = ApiResponse.success("Success", mapper.toDto(review));
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> deleteReview(@PathVariable UUID id, @RequestBody DeleteReviewRequest request) {
        service.deleteReview(id, request);

        var res = ApiResponse.success("Success");
        return ResponseEntity.ok(res);
    }
}
