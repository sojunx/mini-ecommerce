package dev.sojunx.ecommerce.controller;

import dev.sojunx.ecommerce.dto.ApiResponse;
import dev.sojunx.ecommerce.dto.request.ReviewRequest;
import dev.sojunx.ecommerce.mapper.ReviewMapper;
import dev.sojunx.ecommerce.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/reviews/{id}")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReviewController {
    private final ReviewService service;
    private final ReviewMapper mapper;

    @GetMapping
    ResponseEntity<ApiResponse> getReviews(@PathVariable UUID id) {
        var reviews = service.getReviews(id).stream().map(mapper::toDto).toList();

        var res = ApiResponse.success("Success", reviews);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/stats")
    ResponseEntity<ApiResponse> getStats(@PathVariable UUID id) {
        var stats = service.getReviewStats(id);

        var res = ApiResponse.success("Success", stats);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    ResponseEntity<ApiResponse> createReview(@PathVariable UUID id, @RequestBody ReviewRequest request) {
        var review = service.createReview(id, request);

        var res = ApiResponse.success("Success", mapper.toDto(review));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping
    ResponseEntity<ApiResponse> updateReview(@PathVariable UUID id) {

        return null;
    }

    @DeleteMapping
    ResponseEntity<ApiResponse> deleteReview(@PathVariable UUID id, @RequestParam(name = "user_id") UUID userId) {
        service.deleteReview(id, userId);

        var res = ApiResponse.success("Success");
        return ResponseEntity.ok(res);
    }
}
